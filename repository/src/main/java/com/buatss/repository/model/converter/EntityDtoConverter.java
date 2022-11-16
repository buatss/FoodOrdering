package com.buatss.repository.model.converter;

import com.buatss.repository.model.dto.*;
import com.buatss.repository.model.entity.*;
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

    public DrinkExtrasDto convertEntityToDto(DrinkExtrasEntity entity) {
        return modelMapper.map(entity, DrinkExtrasDto.class);
    }

    public DessertDto convertEntityToDto(DessertEntity entity) {
        return modelMapper.map(entity, DessertDto.class);
    }

    public CuisineDto convertEntityToDto(CuisineEntity entity) {
        return modelMapper.map(entity, CuisineDto.class);
    }

    public CustomerDto convertEntityToDto(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerDto.class);
    }

    public OrderDto convertEntityToDto(OrderEntity entity) {
        return modelMapper.map(entity, OrderDto.class);
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

    public TreeSet<DrinkExtrasDto> convertDrinkExtrasEntityListToDrinkExtrasDtoTreeSet(
            List<DrinkExtrasEntity> entities) {
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, DrinkExtrasDto.class))
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(DrinkExtrasDto::getId))));
    }

    public TreeSet<DessertDto> convertDessertEntityListToDessertDtoTreeSet(List<DessertEntity> entities) {
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, DessertDto.class))
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(DessertDto::getId))));
    }

    public TreeSet<CuisineDto> convertCuisineEntityListToCuisineDtoTreeSet(List<CuisineEntity> entities) {
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, CuisineDto.class))
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(CuisineDto::getId))));
    }
}
