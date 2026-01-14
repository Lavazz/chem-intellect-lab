package com.chem.lab.reagentservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="reagent_stock")
public class ReagentStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    UUID reagentId;

    Double quantity;
    String unit;

    LocalDate receivedAt;
    Boolean active;
}