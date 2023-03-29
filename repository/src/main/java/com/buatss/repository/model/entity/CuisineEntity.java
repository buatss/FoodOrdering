package com.buatss.repository.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cuisines")
@NoArgsConstructor
@Data
public class CuisineEntity {
    @OneToMany(mappedBy = "cuisine")
    @ToString.Exclude
    Set<MealEntity> meals;

    @OneToMany(mappedBy = "cuisine")
    @ToString.Exclude
    Set<DessertEntity> desserts;

    @OneToMany(mappedBy = "cuisine")
    @ToString.Exclude
    Set<DrinkEntity> drinks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
