package com.abp.orderservice.model;

import com.abp.orderservice.enumeration.OrderState;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order_ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    private Long inventoryId;
    private Long count;
}
