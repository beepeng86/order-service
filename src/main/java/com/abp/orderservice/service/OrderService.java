package com.abp.orderservice.service;


import com.abp.orderservice.dto.OrderDTO;

public interface OrderService {
    void processOrder(OrderDTO orderDTO);
}
