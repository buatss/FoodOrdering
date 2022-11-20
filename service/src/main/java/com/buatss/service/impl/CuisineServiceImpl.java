package com.buatss.service.impl;

import com.buatss.repository.dao.CuisineRepository;
import com.buatss.repository.dao.DrinkExtrasRepository;
import com.buatss.repository.model.dto.CuisineDto;
import com.buatss.repository.model.dto.DrinkExtrasDto;
import com.buatss.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeSet;

@Service
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository cuisineRepository;

    private final DrinkExtrasRepository drinkExtrasRepository;

    @Autowired
    public CuisineServiceImpl(CuisineRepository repository, DrinkExtrasRepository drinkExtrasRepository) {
        this.cuisineRepository = repository;
        this.drinkExtrasRepository = drinkExtrasRepository;
    }

    @Override
    public TreeSet<CuisineDto> getCuisines() {
        Optional<TreeSet<CuisineDto>> cuisinesResult = cuisineRepository.findPageById(1, 5);
        Optional<TreeSet<DrinkExtrasDto>> drinkExtrasResult = drinkExtrasRepository.findPageById(1, 5);
        if (cuisinesResult.isPresent() && drinkExtrasResult.isPresent()) {
            cuisinesResult.get().forEach(cuisine -> cuisine.setDrinkExtras(drinkExtrasResult.get()));
            return cuisinesResult.get();
        } else {
            throw new RuntimeException("No resources found");
        }
    }
}
