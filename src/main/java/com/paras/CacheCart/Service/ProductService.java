package com.paras.CacheCart.Service;

import com.paras.CacheCart.DTO.ProductResponse;
import com.paras.CacheCart.Entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    ProductResponse mapToDTO(Product product);
}
