package com.example.sensedia.dto;

import com.example.sensedia.enums.DeliveryStatus;

public record OrderResponse(
        Long orderId,
        DeliveryStatus status,
        String deliveryMethod
) {
}