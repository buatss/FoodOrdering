package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.CustomerRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.CustomerDto;
import com.buatss.repository.model.entity.CustomerEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class CustomerRepositoryImpl extends AbstractRepositoryImpl implements CustomerRepository {
    public CustomerRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<CustomerDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(CustomerEntity.class, id)));
        } catch (IllegalArgumentException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerDto> findByExactName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> cq = cb.createQuery(CustomerEntity.class);
        Root<CustomerEntity> customers = cq.from(CustomerEntity.class);
        cq.where(cb.equal(customers.get("name"), name));
        TypedQuery<CustomerEntity> query = entityManager.createQuery(cq);
        try {
            return Optional.of(converter.convertEntityToDto(query.getSingleResult()));
        } catch (NoResultException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<CustomerDto> addCustomer(CustomerEntity customer) {
        try {
            entityManager.persist(customer);
        } catch (PersistenceException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(converter.convertEntityToDto(customer));
    }
}
