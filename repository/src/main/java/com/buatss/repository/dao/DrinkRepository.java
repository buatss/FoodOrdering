package com.buatss.repository.dao;

import com.buatss.repository.model.dto.DrinkDto;

import java.util.Optional;
import java.util.TreeSet;

public interface DrinkRepository {
    Optional<DrinkDto> findById(int id);

    Optional<TreeSet<DrinkDto>> findPageById(int pageNumber, int pageSize);
}
