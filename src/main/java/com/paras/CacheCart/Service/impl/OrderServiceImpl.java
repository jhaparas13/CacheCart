package com.paras.CacheCart.Service.impl;

import com.paras.CacheCart.DTO.OrderRequest;
import com.paras.CacheCart.Entity.Order;
import com.paras.CacheCart.Entity.Product;
import com.paras.CacheCart.Exception.ResourceNotFoundException;
import com.paras.CacheCart.Repository.OrderRepository;
import com.paras.CacheCart.Repository.ProductRepository;
import com.paras.CacheCart.Service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(OrderRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getStockQuantity() < request.getQuantity())
            throw new RuntimeException("Insufficient stock");

        product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
        productRepository.save(product);

        BigDecimal totalPrice =  product.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = Order.builder()
                .productId(product.getId())
                .quantity(request.getQuantity())
                .totalPrice(totalPrice)
                .build();

        return orderRepository.save(order);
    }
}
