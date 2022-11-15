package com.buatss.repository.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    @ToString.Exclude
    Set<MealEntity> meals;

    @OneToMany
    @ToString.Exclude
    Set<DessertEntity> desserts;

    @OneToMany
    @ToString.Exclude
    Set<DrinkEntity> drinks;

    @OneToMany
    @ToString.Exclude
    Set<DrinkExtrasEntity> drinkExtras;

    @Column(scale = 2, nullable = false)
    @NonNull
    private BigDecimal totalPrice;

    @Column
    @CreationTimestamp
    private LocalDateTime purchaseTime;
}
