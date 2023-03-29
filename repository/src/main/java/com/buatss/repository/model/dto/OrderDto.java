package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TreeSet;

@Data
@NoArgsConstructor
public class OrderDto {
    TreeSet<MealDto> meals;

    TreeSet<DessertDto> desserts;

    TreeSet<DrinkDto> drinks;

    TreeSet<DrinkExtrasDto> drinkExtras;

    private Integer id;

    private BigDecimal totalPrice;

    private LocalDateTime purchaseTime;

    @Override
    public String toString() {
        return "OrderDto{" + "meals=" + meals + ", desserts=" + desserts + ", drinks=" + drinks + ", drinkExtras=" +
                drinkExtras + ", id=" + id + ", totalPrice=" + totalPrice + ", purchaseTime=" + purchaseTime + '}';
    }
}
