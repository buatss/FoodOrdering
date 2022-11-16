package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DrinkExtrasDto extends AbstractProductDto<DrinkExtrasDto> {
    @Override
    public String toString() {
        return "DrinkExtrasDto{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
