package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DrinkDto extends AbstractProductDto<DrinkDto> {
    @Override
    public String toString() {
        return "DrinkDto{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
