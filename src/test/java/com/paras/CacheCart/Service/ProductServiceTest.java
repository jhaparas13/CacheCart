package com.paras.CacheCart.Service;

import com.paras.CacheCart.DTO.ProductResponse;
import com.paras.CacheCart.Entity.Product;
import com.paras.CacheCart.Exception.ResourceNotFoundException;
import com.paras.CacheCart.Repository.ProductRepository;
import com.paras.CacheCart.Service.impl.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct() {
        return Product.builder()
                .id(1L)
                .name("Laptop")
                .price(BigDecimal.valueOf(50000))
                .stockQuantity(10)
                .build();
    }

    @Test
    void testGetProductById_success() {
        Product product = sampleProduct();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    void testGetProductById_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,() -> productService.getProductById(1L) );
    }

    @Test
    void testCreateProduct() {
        Product product = sampleProduct();

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = sampleProduct();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_notFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));
    }

}
