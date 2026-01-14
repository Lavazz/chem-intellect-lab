package com.chem.lab.reagentservice.dto.request;

import java.util.UUID;

public record ReagentConsumptionRequestDto(
        UUID reagentId,
        Double amount,
        String unit,
        UUID experimentId
) {
}
