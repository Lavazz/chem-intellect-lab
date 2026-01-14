package com.chem.lab.reagentservice.dto.response;

import java.util.UUID;

public record ReagentAvailabilityResponseDto(
        UUID reagentId,
        Double availableQuantity,
        String unit,
        boolean enough
) {
}
