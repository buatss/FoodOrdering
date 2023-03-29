package com.buatss.repository.dao;

import com.buatss.repository.model.dto.MealDto;

import java.util.Optional;
import java.util.TreeSet;

public interface MealRepository {
    Optional<MealDto> findById(int id);

    Optional<TreeSet<MealDto>> findPageById(int pageNumber, int pageSize);
}
