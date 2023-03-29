package com.buatss.repository.dao;

import com.buatss.repository.model.dto.CuisineDto;

import java.util.Optional;
import java.util.TreeSet;

public interface CuisineRepository {
    Optional<CuisineDto> findById(int id);

    Optional<TreeSet<CuisineDto>> findPageById(int pageNumber, int pageSize);
}
