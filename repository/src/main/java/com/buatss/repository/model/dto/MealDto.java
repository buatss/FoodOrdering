package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MealDto extends AbstractProductDto<MealDto> {
    @Override
    public String toString() {
        return "MealDto{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
