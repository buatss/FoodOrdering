package com.buatss.service.impl;

import com.buatss.repository.dao.OrderRepository;
import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.AbstractProductEntity;
import com.buatss.repository.model.entity.OrderEntity;
import com.buatss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderDto createOrder(OrderEntity order) {
        List<AbstractProductEntity> products = new ArrayList<>();
        products.addAll(new ArrayList<>(order.getMeals()));
        products.addAll(new ArrayList<>(order.getDrinks()));
        products.addAll(new ArrayList<>(order.getDrinkExtras()));
        products.addAll(new ArrayList<>(order.getDesserts()));
        BigDecimal totalPrice =
                products.stream().map(AbstractProductEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);

        Optional<OrderDto> result = repository.addOrder(order);
        if (result.isPresent()){
            return result.get();
        } else {
            throw new RuntimeException("Invalid order");
        }
    }
}
