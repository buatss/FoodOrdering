package com.buatss.repository.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MealEntity extends AbstractProductEntity {
}
