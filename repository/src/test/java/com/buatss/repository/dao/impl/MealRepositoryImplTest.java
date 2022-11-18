package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.MealRepository;
import com.buatss.repository.model.dto.MealDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {AppConfig.class, MealRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/example_data-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("dev")
class MealRepositoryImplTest {

    private final MealDto expected1 = new MealDto();

    @Autowired
    MealRepository mealRepository;

    @BeforeEach
    void setUp() {
        expected1.setId(1);
        expected1.setName("Apple Pie");
        expected1.setPrice(new BigDecimal("10.99"));
    }

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<MealDto> result = mealRepository.findById(1);

        Optional<MealDto> expected = Optional.of(expected1);

        assertTrue(result.isPresent());
        assertEquals(expected, result);
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<MealDto> result = mealRepository.findById(Integer.MAX_VALUE);
        assertFalse(result.isPresent());
    }

    @Test
    void findPageById_found_ReturnsDtoTreeSet() {
        Optional<TreeSet<MealDto>> result = mealRepository.findPageById(1, 2);
        TreeSet<MealDto> expectedSet = new TreeSet<>();

        MealDto expected2 = new MealDto();
        expected2.setId(2);
        expected2.setName("Hunter`s Stew");
        expected2.setPrice(new BigDecimal("12.99"));
        expectedSet.add(expected1);
        expectedSet.add(expected2);

        assertTrue(result.isPresent());
        assertEquals(expectedSet, result.get());
    }

    @Test
    void findPageById_notFound_ReturnsEmptyCollection() {
        Optional<TreeSet<MealDto>> result = mealRepository.findPageById(9999, 9999);
        assertTrue(result.isPresent());
        assertEquals(result.get(), Collections.emptySet());
    }
}
