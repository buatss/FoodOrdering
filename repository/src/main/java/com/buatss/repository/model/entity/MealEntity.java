package com.buatss.repository.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
public class MealEntity extends AbstractProductEntity {
    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private CuisineEntity cuisine;
}
