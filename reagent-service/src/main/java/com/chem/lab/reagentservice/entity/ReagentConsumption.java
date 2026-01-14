package com.chem.lab.reagentservice.entity;


import jakarta.persistence.*;
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
