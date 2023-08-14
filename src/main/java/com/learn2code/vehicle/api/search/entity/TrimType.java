package com.learn2code.vehicle.api.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trim_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrimType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trim_type")
    private String trimType;
}
