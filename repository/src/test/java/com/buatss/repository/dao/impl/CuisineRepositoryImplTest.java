package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.CuisineRepository;
import com.buatss.repository.model.dto.CuisineDto;
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
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {AppConfig.class, CuisineRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/cuisines-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CuisineRepositoryImplTest {
    private final CuisineDto expected1 = new CuisineDto();

    @Autowired
    CuisineRepository repository;

    @BeforeEach
    void setUp() {
        expected1.setId(1);
        expected1.setName("Polish");
        expected1.setDesserts(new TreeSet<>());
        expected1.setDrinks(new TreeSet<>());
        expected1.setMeals(new TreeSet<>());
    }

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<CuisineDto> result = repository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(expected1, result.get());
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<CuisineDto> result = repository.findById(1000);

        assertFalse(result.isPresent());
    }

    @Test
    void findPageById() {
    }
}
