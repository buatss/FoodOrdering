package com.buatss.repository.dao;

import com.buatss.repository.model.dto.CustomerDto;
import com.buatss.repository.model.entity.CustomerEntity;

import java.util.Optional;
import java.util.TreeSet;

public interface CustomerRepository {
    Optional<CustomerDto> findById(int id);

    Optional<CustomerDto> findByExactName(String name);

    Optional<CustomerDto> addCustomer(CustomerEntity customer);
}
