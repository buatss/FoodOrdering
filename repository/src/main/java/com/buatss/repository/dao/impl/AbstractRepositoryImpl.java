package com.buatss.repository.dao.impl;

import com.buatss.repository.model.converter.EntityDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    protected final Logger logger = LoggerFactory.getLogger(AbstractRepositoryImpl.class);

    protected final EntityDtoConverter converter;

    @Autowired
    public AbstractRepositoryImpl(EntityDtoConverter converter) {
        this.converter = converter;
    }
}
