package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DessertDto extends AbstractProductDto<DessertDto> {
    @Override
    public String toString() {
        return "DessertDto{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
