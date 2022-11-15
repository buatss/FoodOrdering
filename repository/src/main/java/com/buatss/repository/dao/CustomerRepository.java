package com.buatss.repository.dao;

import com.buatss.repository.model.dto.CustomerDto;

import java.util.Optional;
import java.util.TreeSet;

public interface CustomerRepository {
    Optional<CustomerDto> findById(int id);

    Optional<TreeSet<CustomerDto>> findPageById(int pageNumber, int pageSize);
}
