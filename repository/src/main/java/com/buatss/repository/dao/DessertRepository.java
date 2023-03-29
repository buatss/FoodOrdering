package com.buatss.repository.dao;

import com.buatss.repository.model.dto.DessertDto;

import java.util.Optional;
import java.util.TreeSet;

public interface DessertRepository {
    Optional<DessertDto> findById(int id);

    Optional<TreeSet<DessertDto>> findPageById(int pageNumber, int pageSize);
}
