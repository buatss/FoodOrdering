package com.buatss.repository.model.converter;

import com.buatss.repository.model.dto.DrinkDto;
import com.buatss.repository.model.dto.MealDto;
import com.buatss.repository.model.entity.DrinkEntity;
import com.buatss.repository.model.entity.MealEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public EntityDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MealDto convertEntityToDto(MealEntity entity) {
        return modelMapper.map(entity, MealDto.class);
    }

    public DrinkDto convertEntityToDto(DrinkEntity entity) {
        return modelMapper.map(entity, DrinkDto.class);
    }

    public TreeSet<MealDto> convertMealEntityListToMealDtoTreeSet(List<MealEntity> entities) {
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, MealDto.class))
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(MealDto::getId))));
    }

    public TreeSet<DrinkDto> convertDrinkEntityListToDrinkDtoTreeSet(List<DrinkEntity> entities) {
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, DrinkDto.class))
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(DrinkDto::getId))));
    }
}
