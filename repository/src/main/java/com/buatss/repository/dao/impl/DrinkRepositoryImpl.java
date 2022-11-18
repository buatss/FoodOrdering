package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.DrinkRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.DrinkDto;
import com.buatss.repository.model.entity.DrinkEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public class DrinkRepositoryImpl extends AbstractRepositoryImpl implements DrinkRepository {

    public DrinkRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<DrinkDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(DrinkEntity.class, id)));
        } catch (IllegalArgumentException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<TreeSet<DrinkDto>> findPageById(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DrinkEntity> cq = cb.createQuery(DrinkEntity.class);
        Root<DrinkEntity> drinks = cq.from(DrinkEntity.class);
        cq.orderBy(cb.asc(drinks.get("id")));
        TypedQuery<DrinkEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return Optional.of(converter.convertDrinkEntityListToDrinkDtoTreeSet(query.getResultList()));
    }
}
