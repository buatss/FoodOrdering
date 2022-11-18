package com.buatss.service.logic.impl;

import com.buatss.repository.dao.CuisineRepository;
import com.buatss.repository.model.dto.CuisineDto;
import com.buatss.service.logic.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeSet;

@Service
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository repository;

    @Autowired
    public CuisineServiceImpl(CuisineRepository repository) {
        this.repository = repository;
    }

    @Override
    public TreeSet<CuisineDto> getCuisines() {
        Optional<TreeSet<CuisineDto>> result = repository.findPageById(1, 5);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("No resources found");
        }
    }
}
