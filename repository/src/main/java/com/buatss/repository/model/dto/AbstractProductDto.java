package com.buatss.repository.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractProductDto<T extends AbstractProductDto<T>> implements Comparable<T> {
    private Integer id;

    private String name;

    private BigDecimal price;

    @Override
    public int compareTo(T o) {
        AbstractProductDto<T> product = o;
        return this.getName().compareTo(product.name);
    }
}
