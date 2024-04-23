package com.example.ecommerce.service;

import com.example.ecommerce.domain.Orders;
import com.example.ecommerce.domain.OrdersGoods;
import com.example.ecommerce.domain.Wishlist;
import com.example.ecommerce.dto.request.OrdersRequest;
import com.example.ecommerce.repository.OrderGoodsRepository;
import com.example.ecommerce.repository.OrdersRepository;
import com.example.ecommerce.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderGoodsRepository orderGoodsRepository;
    private final WishlistRepository wishlistRepository;

    public OrdersService(OrdersRepository ordersRepository, OrderGoodsRepository orderGoodsRepository, WishlistRepository wishlistRepository) {
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
    }

    private List<OrdersGoods> makeOrderGoods(List<Wishlist> wishlists, Orders orders) {
        return wishlists.stream()
                .map(wishlist -> new OrdersGoods(orders.getOrdersId(), wishlist.getGoods(), wishlist.getCount()))
                .toList();
    }

    private Orders makeOrders(List<Wishlist> wishlists) {
        Orders orders = new Orders(wishlists);
        reduceStock(wishlists);
        return orders;
    }


    private void reduceStock(List<Wishlist> wishlists) {
        for (Wishlist wishlist : wishlists) {
            wishlist.getGoods().reduceStock(wishlist.getCount());
        }
    }

}
