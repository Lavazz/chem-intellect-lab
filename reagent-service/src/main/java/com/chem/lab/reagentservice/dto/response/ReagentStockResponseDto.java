package com.chem.lab.reagentservice.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ReagentStockResponseDto(
        UUID id,
        UUID reagentId,
        Double quantity,
        String unit,
        LocalDate receivedAt,
        Boolean active
) {
}
