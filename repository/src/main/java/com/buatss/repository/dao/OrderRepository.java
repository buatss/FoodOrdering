package com.buatss.repository.dao;

import com.buatss.repository.model.dto.OrderDto;

import java.util.Optional;
import java.util.TreeSet;

public interface OrderRepository {
    Optional<OrderDto> findById(int id);

    Optional<TreeSet<OrderDto>> findPageById(int pageNumber, int pageSize);
}
