package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "CustomerDto{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
