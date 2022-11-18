package com.buatss.service.logic;

import com.buatss.repository.model.dto.CuisineDto;

import java.util.TreeSet;

public interface CuisineService {
    TreeSet<CuisineDto> getCuisines();
}
