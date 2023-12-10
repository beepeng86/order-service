package com.abp.orderservice;

import com.abp.orderservice.model.Order_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order_, Long> {

}
