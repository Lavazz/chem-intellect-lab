package com.chem.lab.reagentservice.dto.response;

import java.util.UUID;

public record ReagentResponseDto(
        UUID id,
        String name,
        String formula,
        String casNumber,
        String description
) {}