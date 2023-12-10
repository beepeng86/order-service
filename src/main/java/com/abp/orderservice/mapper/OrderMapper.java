package com.abp.orderservice.mapper;

import com.abp.orderservice.dto.OrderDTO;
import com.abp.orderservice.model.Order_;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order_ fromOrderDTO(OrderDTO orderDTO);
}
