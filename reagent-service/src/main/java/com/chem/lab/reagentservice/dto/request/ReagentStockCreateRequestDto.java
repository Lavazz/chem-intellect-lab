package com.chem.lab.reagentservice.dto.request;

import java.util.UUID;

public record ReagentStockCreateRequestDto(
        UUID reagentId,
        Double quantity,
        String unit
) {
}
