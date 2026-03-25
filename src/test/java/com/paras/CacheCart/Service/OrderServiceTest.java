package com.paras.CacheCart.Service;

import com.paras.CacheCart.DTO.OrderRequest;
import com.paras.CacheCart.Entity.Order;
import com.paras.CacheCart.Entity.Product;
import com.paras.CacheCart.Exception.ResourceNotFoundException;
import com.paras.CacheCart.Repository.OrderRepository;
import com.paras.CacheCart.Repository.ProductRepository;
import com.paras.CacheCart.Service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Product sampleProduct() {
        return Product.builder()
                .id(1L)
                .name("Laptop")
                .description("Electronic")
                .price(BigDecimal.valueOf(50000))
                .stockQuantity(10)
                .build();
    }

    @Test
    void testPlaceOrder_success() {
        Product product = sampleProduct();

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setQuantity(2);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order result = orderService.placeOrder(request);

        assertNotNull(result);
        assertEquals(2, result.getQuantity());
        assertEquals(BigDecimal.valueOf(100000), result.getTotalPrice());

        verify(productRepository).save(product);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_productNotFound() {
        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setQuantity(2);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.placeOrder(request));
    }

    @Test
    void testPlaceOrder_insufficientStock() {
        Product product = sampleProduct();
        product.setStockQuantity(1);

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setQuantity(5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> orderService.placeOrder(request));

    }

    @Test
    void testStockReduction() {
        Product product = sampleProduct();

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setQuantity(3);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        orderService.placeOrder(request);
        assertEquals(7, product.getStockQuantity());
    }
}
