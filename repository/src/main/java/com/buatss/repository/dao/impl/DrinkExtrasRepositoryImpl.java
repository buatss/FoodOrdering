package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.DrinkExtrasRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.DrinkExtrasDto;
import com.buatss.repository.model.entity.DrinkExtrasEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.TreeSet;

public class DrinkExtrasRepositoryImpl extends AbstractRepositoryImpl implements DrinkExtrasRepository {
    public DrinkExtrasRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<DrinkExtrasDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(DrinkExtrasEntity.class, id)));
        } catch (IllegalArgumentException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<TreeSet<DrinkExtrasDto>> findPageById(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DrinkExtrasEntity> cq = cb.createQuery(DrinkExtrasEntity.class);
        Root<DrinkExtrasEntity> drinkExtras = cq.from(DrinkExtrasEntity.class);
        cq.orderBy(cb.asc(drinkExtras.get("id")));
        TypedQuery<DrinkExtrasEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return Optional.of(converter.convertDrinkExtrasEntityListToDrinkExtrasDtoTreeSet(query.getResultList()));
    }
}
