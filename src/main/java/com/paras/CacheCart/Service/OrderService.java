package com.paras.CacheCart.Service;

import com.paras.CacheCart.DTO.OrderRequest;
import com.paras.CacheCart.Entity.Order;

public interface OrderService {
    Order placeOrder(OrderRequest request);
}
