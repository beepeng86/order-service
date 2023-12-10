package com.abp.orderservice.dto;

import com.abp.orderservice.enumeration.OrderState;

public record OrderDTO (Long orderId, OrderState orderState, Long inventoryId, Long count) {
}
