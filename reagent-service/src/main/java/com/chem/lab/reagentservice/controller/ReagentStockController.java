package com.chem.lab.reagentservice.controller;

import com.chem.lab.reagentservice.dto.request.ReagentStockUpdateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentAvailabilityResponseDto;
import com.chem.lab.reagentservice.dto.response.ReagentStockResponseDto;
import com.chem.lab.reagentservice.service.ReagentStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reagent/stock")
public class ReagentStockController {

    private final ReagentStockService reagentStockService;

    @PostMapping
    public ResponseEntity<Void> addStock(@RequestBody ReagentStockUpdateRequestDto dto) {
        reagentStockService.addStock(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{reagentId}")
    public ResponseEntity<List<ReagentStockResponseDto>> getStock(@PathVariable UUID reagentId) {
        List<ReagentStockResponseDto> stock = reagentStockService.getStock(reagentId);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/{reagentId}/availability")
    public ResponseEntity<ReagentAvailabilityResponseDto> checkAvailability(@PathVariable UUID reagentId, @RequestParam Double required) {
        ReagentAvailabilityResponseDto availability = reagentStockService.checkAvailability(reagentId, required);
        return ResponseEntity.ok(availability);
    }
}
