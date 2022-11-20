package com.buatss.service;

import com.buatss.repository.model.dto.CuisineDto;

import java.util.TreeSet;

public interface CuisineService {
    //This should also internally add all drink extras to each cuisine as long as they are common for all of them.
    TreeSet<CuisineDto> getCuisines();
}
