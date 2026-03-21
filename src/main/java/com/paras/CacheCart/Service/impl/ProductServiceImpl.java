package com.paras.CacheCart.Service.impl;

import com.paras.CacheCart.DTO.ProductDTO;
import com.paras.CacheCart.Entity.Product;
import com.paras.CacheCart.Repository.ProductRepository;
import com.paras.CacheCart.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDTO getProductById(Long id) {
        System.out.println("Fetching from DB...");
        Product product = productRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Product not found")));
        return mapToDTO(product);
    }

    @Override
    @Cacheable(value = "products")
    public List<ProductDTO> getAllProducts() {
        System.out.println("Fetching all products from DB...");
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public ProductDTO updateProduct(Long id, Product product) {

        Product existing = productRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Product not found")));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStockQuantity(product.getStockQuantity());

        return mapToDTO(productRepository.save(existing));
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());

        return dto;
    }
}
