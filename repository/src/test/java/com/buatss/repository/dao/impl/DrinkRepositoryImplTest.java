package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.DrinkRepository;
import com.buatss.repository.model.dto.DrinkDto;
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

@ContextConfiguration(classes = {AppConfig.class, DrinkRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/drinks-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DrinkRepositoryImplTest {
    private final DrinkDto expected1 = new DrinkDto();

    @Autowired
    DrinkRepository repository;

    @BeforeEach
    void setUp() {
        expected1.setId(1);
        expected1.setName("Coke");
        expected1.setPrice(new BigDecimal("3.99"));
    }

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<DrinkDto> result = repository.findById(1);
        Optional<DrinkDto> expected = Optional.of(expected1);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<DrinkDto> result = repository.findById(9999);

        assertFalse(result.isPresent());
    }

    @Test
    void findPageById_found_returnsOptionalTreeSetOfDto() {
        Optional<TreeSet<DrinkDto>> result = repository.findPageById(1, 2);

        TreeSet<DrinkDto> expectedSet = new TreeSet<>();
        DrinkDto expected2 = new DrinkDto();
        expected2.setId(2);
        expected2.setName("Fanta");
        expected2.setPrice(new BigDecimal("3.99"));
        expectedSet.add(expected1);
        expectedSet.add(expected2);
        Optional<TreeSet<DrinkDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void findPageById_notFound_returnsOptionalTreeSetEmpty() {
        Optional<TreeSet<DrinkDto>> result = repository.findPageById(9999, 2);

        TreeSet<DrinkDto> expectedSet = new TreeSet<>();
        Optional<TreeSet<DrinkDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }
}
