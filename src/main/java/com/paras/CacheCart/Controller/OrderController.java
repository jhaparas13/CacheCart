package com.paras.CacheCart.Controller;

import com.paras.CacheCart.DTO.ApiResponse;
import com.paras.CacheCart.DTO.OrderRequest;
import com.paras.CacheCart.Entity.Order;
import com.paras.CacheCart.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> placeOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Order>builder()
                        .success(true)
                        .message("Order Created Successfully")
                        .data(orderService.placeOrder(request))
                        .timeStamp(LocalDateTime.now())
                        .build()
        );
    }
}
