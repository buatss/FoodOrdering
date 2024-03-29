package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.OrderRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.OrderDto;
import com.buatss.repository.model.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl extends AbstractRepositoryImpl implements OrderRepository {
    public OrderRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<OrderDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(OrderEntity.class, id)));
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.info(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<OrderDto> addOrder(OrderEntity order) {
        try {
            entityManager.persist(order);
            return Optional.of(converter.convertEntityToDto(order));
        } catch (PersistenceException | NullPointerException e) {
            logger.info(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }
}
