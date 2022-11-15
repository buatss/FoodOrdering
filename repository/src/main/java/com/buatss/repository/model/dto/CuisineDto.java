package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Data
@NoArgsConstructor
public class CuisineDto {
    TreeSet<MealDto> meals;

    TreeSet<DessertDto> desserts;

    TreeSet<DrinkDto> drinks;

    TreeSet<DrinkExtrasDto> drinkExtras;

    private Integer id;

    private String name;
}
