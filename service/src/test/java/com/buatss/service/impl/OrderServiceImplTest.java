package com.buatss.service.impl;

import com.buatss.repository.dao.OrderRepository;
import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void createOrder_created_returnsDto() {
        OrderEntity order = new OrderEntity();
        order.setDesserts(new HashSet<>());
        order.setDrinks(new HashSet<>());
        order.setDrinkExtras(new HashSet<>());
        order.setMeals(new HashSet<>());
        Optional<OrderDto> result = Optional.of(new OrderDto());

        when(repository.addOrder(order)).thenReturn(result);

        assertEquals(orderService.createOrder(order), result.get());
        verify(repository, times(1)).addOrder(order);
    }

    @Test
    void createOrder_notCreated_throwsRuntimeException() {
        OrderEntity order = new OrderEntity();
        order.setDesserts(new HashSet<>());
        order.setDrinks(new HashSet<>());
        order.setDrinkExtras(new HashSet<>());
        order.setMeals(new HashSet<>());
        Optional<OrderDto> result = Optional.empty();

        when(repository.addOrder(order)).thenReturn(result);

        assertThrows(RuntimeException.class, () -> orderService.createOrder(order));
        verify(repository, times(1)).addOrder(order);
    }
}
