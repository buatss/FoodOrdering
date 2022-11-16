package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.MealRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.MealDto;
import com.buatss.repository.model.entity.MealEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.TreeSet;

public class MealRepositoryImpl extends AbstractRepositoryImpl implements MealRepository {
    public MealRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<MealDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(MealEntity.class, id)));
        } catch (IllegalArgumentException e) {
            logger.info(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<TreeSet<MealDto>> findPageById(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MealEntity> cq = cb.createQuery(MealEntity.class);
        Root<MealEntity> meals = cq.from(MealEntity.class);
        cq.orderBy(cb.asc(meals.get("id")));
        TypedQuery<MealEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return Optional.of(converter.convertMealEntityListToMealDtoTreeSet(query.getResultList()));
    }
}
