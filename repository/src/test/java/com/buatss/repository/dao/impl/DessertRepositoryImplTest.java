package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.DessertRepository;
import com.buatss.repository.model.dto.DessertDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {AppConfig.class, DessertRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/desserts-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DessertRepositoryImplTest {
    private final DessertDto expected1 = new DessertDto();

    @Autowired
    DessertRepository repository;

    @BeforeEach
    void setUp() {
        expected1.setId(1);
        expected1.setName("Ice cream");
        expected1.setPrice(new BigDecimal("3.99"));
    }

    @Test
    void findById_found_returnsOptionalOfDto() {
        Optional<DessertDto> result = repository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(expected1, result.get());
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<DessertDto> result = repository.findById(9999);

        assertFalse(result.isPresent());
    }

    @Test
    void findPageById_found_ReturnsOptionalTreeSetOfDto() {
        Optional<TreeSet<DessertDto>> result = repository.findPageById(1, 2);

        TreeSet<DessertDto> expectedSet = new TreeSet<>();
        DessertDto expected2 = new DessertDto();
        expected2.setId(2);
        expected2.setName("Waffles");
        expected2.setPrice(new BigDecimal("4.99"));
        expectedSet.add(expected1);
        expectedSet.add(expected2);
        Optional<TreeSet<DessertDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void findPageById_notFound_returnsOptionalTreeSetEmpty() {
        Optional<TreeSet<DessertDto>> result = repository.findPageById(9999, 2);

        TreeSet<DessertDto> expectedSet = new TreeSet<>();
        Optional<TreeSet<DessertDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }
}
