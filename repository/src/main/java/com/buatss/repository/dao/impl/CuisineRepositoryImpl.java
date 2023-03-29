package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.CuisineRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.CuisineDto;
import com.buatss.repository.model.entity.CuisineEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public class CuisineRepositoryImpl extends AbstractRepositoryImpl implements CuisineRepository {
    public CuisineRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<CuisineDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(CuisineEntity.class, id)));
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<TreeSet<CuisineDto>> findPageById(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CuisineEntity> cq = cb.createQuery(CuisineEntity.class);
        Root<CuisineEntity> cuisines = cq.from(CuisineEntity.class);
        cq.orderBy(cb.asc(cuisines.get("id")));
        TypedQuery<CuisineEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return Optional.of(converter.convertCuisineEntityCollectionToCuisineDtoTreeSet(query.getResultList()));
    }
}
