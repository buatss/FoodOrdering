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
    private Integer id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(scale = 2, nullable = false)
    @NonNull
    private BigDecimal price;
}
