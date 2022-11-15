package com.buatss.repository.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
