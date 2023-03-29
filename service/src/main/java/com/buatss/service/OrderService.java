package com.buatss.service;

import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.OrderEntity;

public interface OrderService {
    //This should sum all product prices and delegate it to repository to persist
    OrderDto createOrder(OrderEntity order);
}
