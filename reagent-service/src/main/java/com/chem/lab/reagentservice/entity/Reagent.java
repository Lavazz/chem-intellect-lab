package com.chem.lab.reagentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reagent")
public class Reagent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    String formula;
    String casNumber;

    String description;
    Boolean active;

    LocalDate createdAt;
    LocalDate updatedAt;
}