package com.example.userservice.domain;

import com.example.userservice.converter.StatusAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    private Long userId;

    private Long totalPrice;

    @Convert(converter = StatusAttributeConverter.class)
    private StatusEnum status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deliveredAt;

    protected Orders() {
    }

    public Orders(List<Wishlist> wishlists) {
        userId = wishlists.get(0).getUserId();
        totalPrice = wishlists.stream()
                .mapToLong(wishlist -> wishlist.getCount() * wishlist.getGoods().getPrice())
                .sum();
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
