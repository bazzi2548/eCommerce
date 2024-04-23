package com.example.ecommerce.domain;

import com.example.ecommerce.converter.StatusAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
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

    protected Orders() {
    }

    public Orders(List<Wishlist> wishlists) {
        userId = wishlists.get(0).getUserId();
        totalPrice = wishlists.stream()
                .mapToLong(wishlist -> wishlist.getCount() * wishlist.getGoods().getPrice())
                .sum();
    }

}
