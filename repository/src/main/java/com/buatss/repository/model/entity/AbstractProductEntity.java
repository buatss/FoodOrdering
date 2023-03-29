package com.buatss.repository.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class AbstractProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    protected Integer id;

    @Column(unique = true, nullable = false)
    @NonNull
    protected String name;

    @Column(scale = 2, nullable = false)
    @NonNull
    protected BigDecimal price;
}
