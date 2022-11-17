package com.buatss.repository.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "desserts")
public class DessertEntity extends AbstractProductEntity {
    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private CuisineEntity cuisine;
}
