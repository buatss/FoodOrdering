package com.buatss.repository.model.dto;

import com.buatss.repository.model.entity.DessertEntity;
import com.buatss.repository.model.entity.DrinkEntity;
import com.buatss.repository.model.entity.DrinkExtrasEntity;
import com.buatss.repository.model.entity.MealEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TreeSet;

@Data
@NoArgsConstructor
public class OrderDto {
    TreeSet<MealEntity> meals;

    TreeSet<DessertEntity> desserts;

    TreeSet<DrinkEntity> drinks;

    TreeSet<DrinkExtrasEntity> drinkExtras;

    private Integer id;

    private BigDecimal totalPrice;

    private LocalDateTime purchaseTime;

    @Override
    public String toString() {
        return "OrderDto{" + "meals=" + meals + ", desserts=" + desserts + ", drinks=" + drinks + ", drinkExtras=" +
                drinkExtras + ", id=" + id + ", totalPrice=" + totalPrice + ", purchaseTime=" + purchaseTime + '}';
    }
}
