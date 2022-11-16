package com.buatss.repository.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public abstract class AbstractProductDto<T extends AbstractProductDto<T>> implements Comparable<T> {
    protected Integer id;

    protected String name;

    protected BigDecimal price;

    @Override
    public int compareTo(T o) {
        AbstractProductDto<T> product = o;
        return this.getName().compareTo(product.name);
    }

    @Override
    public String toString() {
        return "AbstractProductDto{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
