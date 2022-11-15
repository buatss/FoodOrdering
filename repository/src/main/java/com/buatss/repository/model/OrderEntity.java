package com.buatss.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderEntity {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
