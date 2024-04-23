package com.example.ecommerce.service;

import com.example.ecommerce.domain.Orders;
import com.example.ecommerce.domain.OrdersGoods;
import com.example.ecommerce.domain.StatusEnum;
import com.example.ecommerce.repository.OrderGoodsRepository;
import com.example.ecommerce.repository.OrdersRepository;
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
    
    public OrderStatusSchedulerService(OrdersRepository ordersRepository, OrderGoodsRepository orderGoodsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderGoodsRepository = orderGoodsRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void setStatusShipping() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 59, 59));
        List<Orders> orders = ordersRepository.findByStatusAndCreatedAtBetween(StatusEnum.주문완료, startTime, endTime);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.배송중);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void setStatusComplete() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 59, 59));
        List<Orders> orders = ordersRepository.findByStatusAndCreatedAtBetween(StatusEnum.배송중, startTime, endTime);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.배송완료);
            order.setDeliveredAt(LocalDateTime.now());
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void setStatusReturnComplete() {
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 59, 59));
        List<Orders> orders = ordersRepository.findByStatusAndUpdatedAtBetween(StatusEnum.반품중, startTime, endTime);

        for (Orders order : orders) {
            order.setStatus(StatusEnum.반품완료);
            order.setDeliveredAt(LocalDateTime.now());
            increaseStock(order.getOrdersId());
        }
    }

    private void increaseStock(Long ordersId) {
        List<OrdersGoods> goods = orderGoodsRepository.findByOrdersId(ordersId);
        for (OrdersGoods good : goods) {
            good.getGoods().increaseStock(good.getCount());
        }
    }
}
