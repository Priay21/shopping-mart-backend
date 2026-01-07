package org.example.service;

import org.example.entity.Order;

public interface OrderService {
    Order placeOrder(Long customerId, Long productId, Integer quantity);
}