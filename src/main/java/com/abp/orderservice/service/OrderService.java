package com.abp.orderservice.service;


import com.abp.orderservice.dto.OrderDTO;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);

    void processOrder(OrderDTO orderDTO);
}
