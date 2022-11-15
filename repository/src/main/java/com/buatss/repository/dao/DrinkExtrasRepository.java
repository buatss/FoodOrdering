package com.buatss.repository.dao;

import com.buatss.repository.model.dto.DrinkExtrasDto;

import java.util.Optional;
import java.util.TreeSet;

public interface DrinkExtrasRepository {
    Optional<DrinkExtrasDto> findById(int id);

    Optional<TreeSet<DrinkExtrasDto>> findPageById(int pageNumber, int pageSize);
}
