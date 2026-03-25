package com.paras.CacheCart.Repository;

import com.paras.CacheCart.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}