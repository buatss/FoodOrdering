package com.buatss.repository.dao.impl;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.dao.OrderRepository;
import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.OrderEntity;
import org.assertj.core.api.SoftAssertions;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {AppConfig.class, OrderRepositoryImpl.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@Transactional
@Sql(value = "/example_data-dml.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("dev")
class OrderRepositoryImplTest {
    @Autowired
    OrderRepository repository;

    @Test
    void findById_found_returnsOptionalDto() {
        Optional<OrderDto> result = repository.findById(1);
        OrderDto expected = new OrderDto();
        expected.setId(1);
        expected.setDesserts(new TreeSet<>());
        expected.setDrinks(new TreeSet<>());
        expected.setMeals(new TreeSet<>());
        expected.setDrinkExtras(new TreeSet<>());
        expected.setTotalPrice(new BigDecimal("100.99"));

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void findById_notFound_returnsOptionalEmpty() {
        Optional<OrderDto> result = repository.findById(9999);

        assertFalse(result.isPresent());
    }

    @Test
    void addOrder_created_returnsOptionalDto() {
        SoftAssertions softly = new SoftAssertions();
        LocalDateTime now = LocalDateTime.now();
        OrderEntity order = new OrderEntity();
        order.setTotalPrice(new BigDecimal("100.99"));
        OrderDto expected = new OrderDto();
        expected.setId(2);
        expected.setTotalPrice(new BigDecimal("100.99"));

        Optional<OrderDto> result = repository.addOrder(order);

        assertTrue(result.isPresent());
        softly.assertThat(result.get().getId()).isEqualTo(2);
        softly.assertThat(result.get().getDesserts()).isEqualTo(new TreeSet<>());
        softly.assertThat(result.get().getDrinks()).isEqualTo(new TreeSet<>());
        softly.assertThat(result.get().getMeals()).isEqualTo(new TreeSet<>());
        softly.assertThat(result.get().getDrinkExtras()).isEqualTo(new TreeSet<>());
        softly.assertThat(result.get().getPurchaseTime()).isAfterOrEqualTo(now);
        softly.assertAll();
    }

    @Test
    void addOrder_notCreated_returnsOptionalEmpty() {
        OrderEntity order = new OrderEntity();
        order.setId(1);
        order.setTotalPrice(new BigDecimal("100.9999"));

        Optional<OrderDto> result = repository.addOrder(order);

        assertFalse(result.isPresent());
    }
}
