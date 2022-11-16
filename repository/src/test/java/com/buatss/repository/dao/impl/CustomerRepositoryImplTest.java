package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.CustomerRepository;
import com.buatss.repository.model.dto.CustomerDto;
import com.buatss.repository.model.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {AppConfig.class, CustomerRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/customers-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerRepositoryImplTest {
    private final CustomerDto expected = new CustomerDto();

    @Autowired
    CustomerRepository repository;

    @BeforeEach
    void setUp() {
        expected.setId(1);
        expected.setName("buatss");
    }

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<CustomerDto> result = repository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<CustomerDto> result = repository.findById(9999);

        assertFalse(result.isPresent());
    }

    @Test
    void findByExactName_found_returnsOptionalDto() {
        Optional<CustomerDto> result = repository.findByExactName("buatss");

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void findByExactName_notFound_returnsOptionalEmpty() {
        Optional<CustomerDto> result = repository.findByExactName("notExistingName");

        assertFalse(result.isPresent());
    }

    @Test
    void addCustomer_created_returnsOptionalDto() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("Bardin Goreksson");
        CustomerDto expected = new CustomerDto();
        expected.setId(4);
        expected.setName("Bardin Goreksson");

        Optional<CustomerDto> result = repository.addCustomer(customer);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void addCustomer_notCreated_returnOptionalEmpty() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("buatss");

        Optional<CustomerDto> result = repository.addCustomer(customer);

        assertFalse(result.isPresent());
    }

}
