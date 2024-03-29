package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Data
@NoArgsConstructor
public class CuisineDto implements Comparable<CuisineDto> {
    TreeSet<MealDto> meals;

    TreeSet<DessertDto> desserts;

    TreeSet<DrinkDto> drinks;

    TreeSet<DrinkExtrasDto> drinkExtras;

    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "CuisineDto{" + "meals=" + meals + ", desserts=" + desserts + ", drinks=" + drinks + ", drinkExtras=" +
                drinkExtras + ", id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public int compareTo(CuisineDto o) {
        return this.id.compareTo(o.id);
    }
}
