package com.chem.lab.reagentservice.dto.request;

import java.util.UUID;

public record ReagentStockUpdateRequestDto(
        UUID reagentId,
        Double quantity,
        String unit
) {
}
