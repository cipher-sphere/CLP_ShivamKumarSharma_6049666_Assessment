package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.Product;
import com.example.orderservice.dto.User;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private static final String USER_SERVICE_URL    = "http://user-service/users/";
    private static final String PRODUCT_SERVICE_URL = "http://product-service/products/";

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public OrderResponse createOrder(OrderRequest request, String correlationId) {
        logger.info("[CorrelationID: {}] Creating order for userId={} productId={} qty={}",
                correlationId, request.getUserId(), request.getProductId(), request.getQuantity());

        // Call User Service
        logger.info("[CorrelationID: {}] Calling User Service for userId={}", correlationId, request.getUserId());
        User user = restTemplate.getForObject(USER_SERVICE_URL + request.getUserId(), User.class);

        // Call Product Service
        logger.info("[CorrelationID: {}] Calling Product Service for productId={}", correlationId, request.getProductId());
        Product product = restTemplate.getForObject(PRODUCT_SERVICE_URL + request.getProductId(), Product.class);

        // Calculate total
        double totalPrice = product.getPrice() * request.getQuantity();

        // Persist order
        Order savedOrder = orderRepository.save(
                new Order(request.getUserId(), request.getProductId(), request.getQuantity(), totalPrice)
        );

        logger.info("[CorrelationID: {}] Order created with id={}", correlationId, savedOrder.getId());

        return new OrderResponse(
                savedOrder.getId(),
                user.getName(),
                product.getName(),
                request.getQuantity(),
                totalPrice
        );
    }
}