package com.chem.lab.reagentservice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReagentConsumptionResponseDto(
        UUID id,
        UUID reagentId,
        Double amount,
        String unit,
        UUID experimentId,
        LocalDateTime consumedAt
) {
}
