package com.abp.orderservice.controller;

import com.abp.orderservice.dto.OrderDTO;
import com.abp.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/order")
public class OrderServiceController {
    private final OrderService orderService;

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            log.trace("Order receive: {}", orderDTO);
            orderService.processOrder(orderDTO);
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok("Order placed sucessfully");
    }
}
