package com.chem.lab.reagentservice.dto.request;

public record ReagentCreateRequestDto(
        String name,
        String formula,
        String casNumber,
        String description
) {
}
