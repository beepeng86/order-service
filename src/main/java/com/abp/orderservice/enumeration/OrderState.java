package com.abp.orderservice.enumeration;

public enum OrderState {
    CREATED,
    PLACED,
    PAYMENT,
    MANUAL_HANDLING,
    PACK_DELIVERY,
    DELIVERED,
    CANCELLED;

    private OrderState onSuccess;
    private OrderState onFailure;

    static {
        CREATED.onSuccess = PLACED;
        CREATED.onFailure = MANUAL_HANDLING;
        PLACED.onSuccess = PAYMENT;
        PLACED.onFailure = MANUAL_HANDLING;
        PAYMENT.onSuccess = PACK_DELIVERY;
        PAYMENT.onFailure = MANUAL_HANDLING;
    }

    public OrderState onSuccess() {
        return this.onSuccess;
    }

    public OrderState onFailure() {
        return this.onFailure;
    }

}
