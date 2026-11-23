package com.example.sensedia.service;

import com.example.sensedia.dto.OrderResponse;
import com.example.sensedia.enums.DeliveryStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final Map<Long, OrderResponse> orders = Map.of(
            12345L,
            new OrderResponse(
                    12345L,
                    DeliveryStatus.PENDING,
                    "EXPRESS"
            ),
            12346L,
            new OrderResponse(
                    12346L,
                    DeliveryStatus.DELIVERED,
                    "STANDARD"
            )
    );

    public Optional<OrderResponse> findById(Long orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }
}