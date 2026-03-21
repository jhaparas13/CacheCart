package com.paras.CacheCart.Service;

import com.paras.CacheCart.DTO.ProductDTO;
import com.paras.CacheCart.Entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    ProductDTO mapToDTO(Product product);
}
