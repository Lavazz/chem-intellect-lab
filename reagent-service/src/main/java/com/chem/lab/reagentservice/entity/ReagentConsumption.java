package com.chem.lab.reagentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reagent_consumption")
public class ReagentConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    UUID reagentId;

    Double amount;
    String unit;

    UUID experimentId;
    LocalDateTime consumedAt;
}
