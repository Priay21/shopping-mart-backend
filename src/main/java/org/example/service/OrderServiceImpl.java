package org.example.service;

import org.example.entity.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order placeOrder(Long customerId, Long productId, Integer quantity) {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (customer == null || product == null) {
            throw new RuntimeException("Customer or Product not found");
        }

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        // reduce stock
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        // create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(product.getPrice() * quantity);
        orderRepository.save(order);

        // create order item
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice());
        orderItemRepository.save(item);

        return order;
    }
}