package com.chem.lab.reagentservice.controller;

import com.chem.lab.reagentservice.dto.request.ReagentConsumptionRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentConsumptionResponseDto;
import com.chem.lab.reagentservice.service.ReagentConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expirement/consumption")
public class ReagentConsumptionController {

    private final ReagentConsumptionService reagentConsumptionService;

    @PostMapping
    public ResponseEntity<Void> registerConsumption(@RequestBody ReagentConsumptionRequestDto dto) {
        reagentConsumptionService.registerConsumption(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/reagent/{reagentId}")
    public ResponseEntity<List<ReagentConsumptionResponseDto>> getByReagent(@PathVariable UUID reagentId) {
        List<ReagentConsumptionResponseDto> consumptionList = reagentConsumptionService.getByReagent(reagentId);
        return ResponseEntity.ok(consumptionList);
    }

    @GetMapping("/experiment/{experimentId}")
    public ResponseEntity<List<ReagentConsumptionResponseDto>> getByExperiment(@PathVariable UUID experimentId) {
        List<ReagentConsumptionResponseDto> consumptionList = reagentConsumptionService.getByExperiment(experimentId);
        return ResponseEntity.ok(consumptionList);
    }
}
