package org.example.controller;

import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order placeOrder(@RequestParam Long customerId,
                            @RequestParam Long productId,
                            @RequestParam Integer quantity) {

        return service.placeOrder(customerId, productId, quantity);
    }
}