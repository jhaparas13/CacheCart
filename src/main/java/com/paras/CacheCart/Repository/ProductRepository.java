package com.paras.CacheCart.Repository;

import com.paras.CacheCart.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findByNameContainingIgnoreCase(String name);
}
