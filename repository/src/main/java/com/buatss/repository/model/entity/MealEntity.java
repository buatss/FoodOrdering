package com.buatss.repository.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MealEntity extends AbstractProductEntity {
    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private CuisineEntity cuisine;
}
