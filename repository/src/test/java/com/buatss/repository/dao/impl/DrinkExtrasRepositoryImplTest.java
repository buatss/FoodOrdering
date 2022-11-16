package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.DrinkExtrasRepository;
import com.buatss.repository.model.dto.DrinkExtrasDto;
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

@ContextConfiguration(classes = {AppConfig.class, DrinkExtrasRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/drinkExtras-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DrinkExtrasRepositoryImplTest {
    private final DrinkExtrasDto expected1 = new DrinkExtrasDto();

    @Autowired
    DrinkExtrasRepository repository;

    @BeforeEach
    void setUp() {
        expected1.setId(1);
        expected1.setName("Lemon");
        expected1.setPrice(new BigDecimal("0.99"));
    }

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<DrinkExtrasDto> result = repository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(expected1, result.get());
    }

    @Test
    void findById_notFound_ReturnsOptionalEmpty() {
        Optional<DrinkExtrasDto> result = repository.findById(1000);

        assertFalse(result.isPresent());
    }

    @Test
    void findPageById_found_returnsOptionalTreeSetOfDto() {
        Optional<TreeSet<DrinkExtrasDto>> result = repository.findPageById(1, 2);

        TreeSet<DrinkExtrasDto> expectedSet = new TreeSet<>();
        DrinkExtrasDto expected2 = new DrinkExtrasDto();
        expected2.setId(2);
        expected2.setName("Ice cubes");
        expected2.setPrice(new BigDecimal("0.49"));
        expectedSet.add(expected1);
        expectedSet.add(expected2);
        Optional<TreeSet<DrinkExtrasDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void findPageById_notFound_returnsOptionalEmptyTreeSet() {
        Optional<TreeSet<DrinkExtrasDto>> result = repository.findPageById(9999, 2);

        TreeSet<DrinkExtrasDto> expectedSet = new TreeSet<>();
        Optional<TreeSet<DrinkExtrasDto>> expected = Optional.of(expectedSet);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

}
