package com.chem.lab.reagentservice.service;

import com.chem.lab.reagentservice.dto.request.ReagentStockUpdateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentAvailabilityResponseDto;
import com.chem.lab.reagentservice.dto.response.ReagentStockResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReagentStockService {
    void addStock(ReagentStockUpdateRequestDto dto);
    List<ReagentStockResponseDto> getStock(UUID reagentId);
    ReagentAvailabilityResponseDto checkAvailability(UUID reagentId, Double required);
    void consume(UUID uuid, Double amount);
}
