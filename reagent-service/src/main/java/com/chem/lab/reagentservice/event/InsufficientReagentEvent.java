package com.chem.lab.reagentservice.event;

import java.util.UUID;

public record InsufficientReagentEvent(
        UUID reagentId,
        double missingAmount
) {
}