package com.abp.orderservice.service;

public interface InventoryService {
    boolean bookInventory(Long inventoryId, Long count);
}
