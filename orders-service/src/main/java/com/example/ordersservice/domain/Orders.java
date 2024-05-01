package com.example.ordersservice.domain;

import com.example.ordersservice.converter.StatusAttributeConverter;
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

    public Orders(List<Wishlist> wishlists, Long totalPrice) {
        userId = wishlists.get(0).getUserId();
        this.totalPrice = totalPrice;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
