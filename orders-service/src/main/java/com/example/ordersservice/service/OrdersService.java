package com.example.ordersservice.service;

import com.example.ordersservice.domain.*;
import com.example.ordersservice.dto.request.OrdersRequest;
import com.example.ordersservice.dto.response.OrderGoodsResponse;
import com.example.ordersservice.dto.response.OrdersResponse;
import com.example.ordersservice.repository.OrderGoodsRepository;
import com.example.ordersservice.repository.OrdersRepository;
import com.example.ordersservice.repository.UserRepository;
import com.example.ordersservice.repository.WishlistRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class OrdersService {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final OrderGoodsRepository orderGoodsRepository;
    private final WishlistRepository wishlistRepository;

    public OrdersService(UserRepository userRepository, OrdersRepository ordersRepository, OrderGoodsRepository orderGoodsRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.orderGoodsRepository = orderGoodsRepository;
        this.wishlistRepository = wishlistRepository;
    }

    public void requestOrders(OrdersRequest request) {
        List<Wishlist> wishlists = wishlistRepository.findAllById(request.getWishlists());
        Orders orders = makeOrders(wishlists);
        ordersRepository.save(orders);

        List<OrdersGoods> goods = makeOrderGoods(wishlists, orders);
        orderGoodsRepository.saveAll(goods);
        deleteWishlist(wishlists);
    }

    public List<OrdersResponse> getOrders(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());
        List<Orders> orders = ordersRepository.findByUserId(user.getUserId());

        return orders.stream()
                .map(OrdersResponse::new)
                .toList();
    }

    public List<OrderGoodsResponse> getDetailOrders(long ordersId) {
        return orderGoodsRepository.findByOrdersId(ordersId)
                .stream()
                .map(OrderGoodsResponse::new)
                .toList();
    }

    public void cancelOrders(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).get();
        if (orders.getStatus() != StatusEnum.주문완료) {
            throw new IllegalArgumentException("can't cancel orders");
        }

        orders.setStatus(StatusEnum.취소완료);
        List<OrdersGoods> goods = orderGoodsRepository.findByOrdersId(orders.getOrdersId());
        increaseStock(goods);
    }

    public void returnOrders(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).get();
        if (orders.getStatus() != StatusEnum.배송완료) {
            throw new IllegalArgumentException("can't return orders");
        }
        LocalDate delivered = orders.getDeliveredAt().toLocalDate();
        LocalDate now = LocalDateTime.now().toLocalDate();
        Period diff = Period.between(delivered, now);

        if (diff.getDays() > 1) {
            throw new IllegalArgumentException("can't return orders");
        }
        orders.setStatus(StatusEnum.반품중);
    }

    private Orders makeOrders(List<Wishlist> wishlists) {
        Orders orders = new Orders(wishlists);
        reduceStock(wishlists);
        return orders;
    }

    private void deleteWishlist(List<Wishlist> wishlists) {
        wishlistRepository.deleteAllById(wishlists.stream().map(Wishlist::getWishlistId).toList());
    }

    private List<OrdersGoods> makeOrderGoods(List<Wishlist> wishlists, Orders orders) {
        return wishlists.stream()
                .map(wishlist -> new OrdersGoods(orders.getOrdersId(), wishlist.getGoods(), wishlist.getCount()))
                .toList();
    }

    private void reduceStock(List<Wishlist> wishlists) {
        for (Wishlist wishlist : wishlists) {
            wishlist.getGoods().reduceStock(wishlist.getCount());
        }
    }

    private void increaseStock(List<OrdersGoods> goods) {
        for (OrdersGoods good : goods) {
            good.getGoods().increaseStock(good.getCount());
        }
    }
}
