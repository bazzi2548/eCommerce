package com.example.ordersservice.service;

import com.example.ordersservice.client.GoodsClient;
import com.example.ordersservice.domain.Orders;
import com.example.ordersservice.domain.OrdersGoods;
import com.example.ordersservice.domain.StatusEnum;
import com.example.ordersservice.repository.OrderGoodsRepository;
import com.example.ordersservice.repository.OrdersRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class OrderStatusSchedulerService {

    private final OrdersRepository ordersRepository;
    private final OrderGoodsRepository orderGoodsRepository;
    private final GoodsClient goodsClient;
    public OrderStatusSchedulerService(OrdersRepository ordersRepository, OrderGoodsRepository orderGoodsRepository, GoodsClient goodsClient) {
        this.ordersRepository = ordersRepository;
        this.orderGoodsRepository = orderGoodsRepository;
        this.goodsClient = goodsClient;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void setStatusShipping() {
        List<Orders> orders = getOrders(StatusEnum.주문완료);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.배송중);
        }
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void setStatusComplete() {
        List<Orders> orders = getOrders(StatusEnum.배송중);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.배송완료);
            order.setDeliveredAt(LocalDateTime.now());
        }
    }

    @Scheduled(cron = "2 0 0 * * *")
    public void setStatusReturnComplete() {
        List<Orders> orders = getOrders(StatusEnum.반품중);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.반품완료);
            order.setDeliveredAt(LocalDateTime.now());
            increaseStock(order.getOrdersId());
        }
    }

    private void increaseStock(Long ordersId) {
        List<OrdersGoods> goods = orderGoodsRepository.findByOrdersId(ordersId);
        for (OrdersGoods good : goods) {
            goodsClient.increaseGoods(good.getGoodsId(), good.getCount());
        }
    }

    private List<Orders> getOrders(StatusEnum status) {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 59, 59));
        return ordersRepository.findByStatusAndUpdatedAtBetween(status, startTime, endTime);
    }
}
