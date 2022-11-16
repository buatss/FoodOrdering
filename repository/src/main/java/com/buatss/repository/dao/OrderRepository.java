package com.buatss.repository.dao;

import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepository {
    Optional<OrderDto> findById(int id);

    Optional<OrderDto> addOrder(OrderEntity order);
}
