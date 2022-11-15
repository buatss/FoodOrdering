package com.buatss.repository.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class CuisineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany
    @ToString.Exclude
    Set<MealEntity> meals;

    @OneToMany
    @ToString.Exclude
    Set<DessertEntity> desserts;

    @OneToMany
    @ToString.Exclude
    Set<DrinkEntity> drinks;
}
