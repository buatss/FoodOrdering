package com.buatss.service;

import com.buatss.repository.model.dto.CuisineDto;

import java.util.TreeSet;

public interface CuisineService {
    TreeSet<CuisineDto> getCuisines();
}
