package com.buatss.repository.dao.impl;

import com.buatss.repository.dao.DessertRepository;
import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.DessertDto;
import com.buatss.repository.model.entity.DessertEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.TreeSet;

@Repository
public class DessertRepositoryImpl extends AbstractRepositoryImpl implements DessertRepository {
    public DessertRepositoryImpl(EntityDtoConverter converter) {
        super(converter);
    }

    @Override
    @Transactional
    public Optional<DessertDto> findById(int id) {
        try {
            return Optional.of(converter.convertEntityToDto(entityManager.find(DessertEntity.class, id)));
        } catch (IllegalArgumentException e) {
            logger.debug(e.getClass() + " " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<TreeSet<DessertDto>> findPageById(int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DessertEntity> cq = cb.createQuery(DessertEntity.class);
        Root<DessertEntity> desserts = cq.from(DessertEntity.class);
        cq.orderBy(cb.asc(desserts.get("id")));
        TypedQuery<DessertEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return Optional.of(converter.convertDessertEntityCollectionToDessertDtoTreeSet(query.getResultList()));
    }
}
