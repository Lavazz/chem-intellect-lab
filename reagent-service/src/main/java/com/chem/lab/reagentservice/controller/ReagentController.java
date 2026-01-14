package com.chem.lab.reagentservice.controller;

import com.chem.lab.reagentservice.dto.request.ReagentCreateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentResponseDto;
import com.chem.lab.reagentservice.service.ReagentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reagent")
public class ReagentController {

    private final ReagentService reagentService;

    @GetMapping
    public ResponseEntity<List<ReagentResponseDto>> getAllReagents() {
        List<ReagentResponseDto> reagents = reagentService.getAllReagents();
        return ResponseEntity.ok(reagents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReagentResponseDto> getReagentById(@PathVariable UUID id) {
        ReagentResponseDto reagent = reagentService.getReagent(id);
        return ResponseEntity.ok(reagent);
    }

    @PostMapping
    public ResponseEntity<ReagentResponseDto> createReagent(@RequestBody ReagentCreateRequestDto dto) {
        ReagentResponseDto reagent = reagentService.createReagent(dto);
        return ResponseEntity.status(201).body(reagent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReagentResponseDto> updateReagent(@PathVariable UUID id, @RequestBody ReagentCreateRequestDto dto) {
        ReagentResponseDto reagent = reagentService.updateReagent(id, dto);
        return ResponseEntity.ok(reagent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReagent(@PathVariable UUID id) {
        reagentService.deleteReagent(id);
        return ResponseEntity.noContent().build();
    }
}
