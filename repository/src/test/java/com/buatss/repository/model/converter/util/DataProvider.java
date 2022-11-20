package com.buatss.repository.model.converter.util;

import com.buatss.repository.model.dto.*;
import com.buatss.repository.model.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DataProvider {
    private final LocalDateTime time = LocalDateTime.now();

    public CuisineEntity provideCuisineEntity() {
        CuisineEntity entity = new CuisineEntity();
        entity.setId(1);
        entity.setName("Polish");
        entity.setDesserts(Set.of(provideDessertEntity()));
        entity.setMeals(Set.of(provideMealEntity()));
        entity.setDrinks(Set.of(provideDrinkEntity()));
        return entity;
    }

    public CuisineDto provideCuisineDto() {
        CuisineDto dto = new CuisineDto();
        dto.setId(1);
        dto.setName("Polish");
        dto.setDesserts(provideTreeSetOfDessertDto());
        dto.setMeals(provideTreeSetOfMealDto());
        dto.setDrinks(provideTreeSetOfDrinkDto());
        return dto;
    }

    public DessertEntity provideDessertEntity() {
        DessertEntity entity = new DessertEntity();
        entity.setId(1);
        entity.setName("Ice cream");
        entity.setPrice(new BigDecimal("4.99"));
        return entity;
    }

    public DessertDto provideDessertDto() {
        DessertDto dto = new DessertDto();
        dto.setId(1);
        dto.setName("Ice cream");
        dto.setPrice(new BigDecimal("4.99"));
        return dto;
    }

    public DrinkEntity provideDrinkEntity() {
        DrinkEntity entity = new DrinkEntity();
        entity.setId(1);
        entity.setName("Cola");
        entity.setPrice(new BigDecimal("3.99"));
        return entity;
    }

    public DrinkDto provideDrinkDto() {
        DrinkDto dto = new DrinkDto();
        dto.setId(1);
        dto.setName("Cola");
        dto.setPrice(new BigDecimal("3.99"));
        return dto;
    }

    public DrinkExtrasEntity provideDrinkExtrasEntity() {
        DrinkExtrasEntity entity = new DrinkExtrasEntity();
        entity.setId(1);
        entity.setName("Lemon");
        entity.setPrice(new BigDecimal("0.99"));
        return entity;
    }

    public DrinkExtrasDto provideDrinkExtrasDto() {
        DrinkExtrasDto dto = new DrinkExtrasDto();
        dto.setId(1);
        dto.setName("Lemon");
        dto.setPrice(new BigDecimal("0.99"));
        return dto;
    }

    public MealEntity provideMealEntity() {
        MealEntity entity = new MealEntity();
        entity.setId(1);
        entity.setName("Hunter`s stew");
        entity.setPrice(new BigDecimal("11.99"));
        return entity;
    }

    public MealDto provideMealDto() {
        MealDto dto = new MealDto();
        dto.setId(1);
        dto.setName("Hunter`s stew");
        dto.setPrice(new BigDecimal("11.99"));
        return dto;
    }

    public OrderEntity provideOrderEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setTotalPrice(new BigDecimal("200.47"));
        entity.setPurchaseTime(time);
        entity.setDesserts(Set.of(provideDessertEntity()));
        entity.setDrinks(Set.of(provideDrinkEntity()));
        entity.setDrinkExtras(Set.of(provideDrinkExtrasEntity()));
        entity.setMeals(Set.of(provideMealEntity()));
        return entity;
    }

    public OrderDto provideOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setId(1);
        dto.setTotalPrice(new BigDecimal("200.47"));
        dto.setPurchaseTime(time);
        dto.setDesserts(provideTreeSetOfDessertDto());
        dto.setDrinks(provideTreeSetOfDrinkDto());
        dto.setDrinkExtras(provideTreeSetOfDrinkExtrasDto());
        dto.setMeals(provideTreeSetOfMealDto());
        return dto;
    }

    public List<MealEntity> provideMealEntityList() {
        return List.of(provideMealEntity());
    }

    public List<DrinkEntity> provideDrinkEntityList() {
        return List.of(provideDrinkEntity());
    }

    public List<DrinkExtrasEntity> provideDrinkExtrasEntityList() {
        return List.of(provideDrinkExtrasEntity());
    }

    public List<DessertEntity> provideDessertEntityList() {
        return List.of(provideDessertEntity());
    }

    public List<CuisineEntity> provideCuisineEntityList() {
        return List.of(provideCuisineEntity());
    }

    public TreeSet<DessertDto> provideTreeSetOfDessertDto() {
        TreeSet<DessertDto> set = new TreeSet<>();
        set.add(provideDessertDto());
        return set;
    }

    public TreeSet<DrinkDto> provideTreeSetOfDrinkDto() {
        TreeSet<DrinkDto> set = new TreeSet<>();
        set.add(provideDrinkDto());
        return set;
    }

    public TreeSet<DrinkExtrasDto> provideTreeSetOfDrinkExtrasDto() {
        TreeSet<DrinkExtrasDto> set = new TreeSet<>();
        set.add(provideDrinkExtrasDto());
        return set;
    }

    public TreeSet<MealDto> provideTreeSetOfMealDto() {
        TreeSet<MealDto> set = new TreeSet<>();
        set.add(provideMealDto());
        return set;
    }

    public TreeSet<CuisineDto> provideTreeSetOfCuisineDto() {
        TreeSet<CuisineDto> set = new TreeSet<>();
        set.add(provideCuisineDto());
        return set;
    }
}
