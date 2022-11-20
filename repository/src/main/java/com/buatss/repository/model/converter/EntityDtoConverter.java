package com.buatss.repository.model.converter;

import com.buatss.repository.model.dto.*;
import com.buatss.repository.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    public MealDto convertEntityToDto(MealEntity entity) {
        MealDto dto = new MealDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public DrinkDto convertEntityToDto(DrinkEntity entity) {
        DrinkDto dto = new DrinkDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public DrinkExtrasDto convertEntityToDto(DrinkExtrasEntity entity) {
        DrinkExtrasDto dto = new DrinkExtrasDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public DessertDto convertEntityToDto(DessertEntity entity) {
        DessertDto dto = new DessertDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public CuisineDto convertEntityToDto(CuisineEntity entity) {
        CuisineDto dto = new CuisineDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMeals(convertMealEntityCollectionToMealDtoTreeSet(entity.getMeals()));
        dto.setDrinks(convertDrinkEntityCollectionToDrinkDtoTreeSet(entity.getDrinks()));
        dto.setDesserts(convertDessertEntityCollectionToDessertDtoTreeSet(entity.getDesserts()));
        return dto;
    }

    public MealEntity convertDtoToEntity(MealDto dto) {
        MealEntity entity = new MealEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public DessertEntity convertDtoToEntity(DessertDto dto) {
        DessertEntity entity = new DessertEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public DrinkEntity convertDtoToEntity(DrinkDto dto) {
        DrinkEntity entity = new DrinkEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public DrinkExtrasEntity convertDtoToEntity(DrinkExtrasDto dto) {
        DrinkExtrasEntity entity = new DrinkExtrasEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public OrderDto convertEntityToDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setPurchaseTime(entity.getPurchaseTime());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setDesserts(convertDessertEntityCollectionToDessertDtoTreeSet(entity.getDesserts()));
        dto.setDrinks(convertDrinkEntityCollectionToDrinkDtoTreeSet(entity.getDrinks()));
        dto.setMeals(convertMealEntityCollectionToMealDtoTreeSet(entity.getMeals()));
        dto.setDrinkExtras(convertDrinkExtrasEntityCollectionToDrinkExtrasDtoTreeSet(entity.getDrinkExtras()));
        return dto;
    }

    public TreeSet<MealDto> convertMealEntityCollectionToMealDtoTreeSet(Collection<MealEntity> entities) {
        return entities.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MealDto::getName))));
    }

    public TreeSet<DrinkDto> convertDrinkEntityCollectionToDrinkDtoTreeSet(Collection<DrinkEntity> entities) {
        return entities.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DrinkDto::getName))));
    }

    public TreeSet<DrinkExtrasDto> convertDrinkExtrasEntityCollectionToDrinkExtrasDtoTreeSet(
            Collection<DrinkExtrasEntity> entities) {
        return entities.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DrinkExtrasDto::getName))));
    }

    public TreeSet<DessertDto> convertDessertEntityCollectionToDessertDtoTreeSet(Collection<DessertEntity> entities) {
        return entities.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DessertDto::getName))));
    }

    public TreeSet<CuisineDto> convertCuisineEntityCollectionToCuisineDtoTreeSet(Collection<CuisineEntity> entities) {
        return entities.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(CuisineDto::getId))));
    }
}
