package com.abp.orderservice.service;

import com.abp.orderservice.OrderRepository;
import com.abp.orderservice.dto.OrderDTO;
import com.abp.orderservice.enumeration.OrderState;
import com.abp.orderservice.mapper.OrderMapper;
import com.abp.orderservice.model.Order_;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;

    @Override
    public void processOrder(OrderDTO orderDTO) {
        // 1. Simple validation, logic in validation service
        // 2. create order to db
        // 3. call inventory service to book
            // 3a. process payment
            // 3b. low in inventory - set status to manual handling and send message to restock or inform customer for order cancellation
            // 3c. unexpected error - retry, after exhausted send to manual handling
        // 4. send a message to trigger email sending to notify user about the order status: success?

        if (!isValidOrder(orderDTO)) {
            throw new IllegalArgumentException("Invalid order construct");
        }

        Order_ order = createAndSaveOrderToDB(orderDTO);
        log.trace("Order created and saved to DB");

        Boolean isInverntoryBookSuccessful = inventoryService.bookInventory(order.getInventoryId(), order.getCount());

        if (isInverntoryBookSuccessful) {
            // 3a.
            paymentService.processPayment();
            order.setOrderState(OrderState.PAYMENT);
        } else {
            // 3b. 3c.
            order.setOrderState(OrderState.MANUAL_HANDLING);
        }
        orderRepository.saveAndFlush(order);
        // 4.

    }

    private Order_ createAndSaveOrderToDB(OrderDTO orderDTO) {
        Order_ order = orderMapper.fromOrderDTO(orderDTO);
        order.setOrderState(OrderState.CREATED);
        return orderRepository.saveAndFlush(order);
    }

    private boolean isValidOrder(OrderDTO orderDTO) {
        // TODO: should be more complex logic here, might need to be placed in a separate service
        return Objects.nonNull(orderDTO)
                && Objects.isNull(orderDTO.orderId())
                && Objects.isNull(orderDTO.orderState());
    }
}
