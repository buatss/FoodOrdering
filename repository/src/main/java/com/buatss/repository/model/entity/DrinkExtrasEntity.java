package com.buatss.repository.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "drinkExtras")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DrinkExtrasEntity extends AbstractProductEntity {
}
