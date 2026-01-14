package com.chem.lab.reagentservice.service.impl;

import com.chem.lab.reagentservice.dto.request.ReagentCreateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentResponseDto;
import com.chem.lab.reagentservice.entity.Reagent;
import com.chem.lab.reagentservice.repository.ReagentRepository;
import com.chem.lab.reagentservice.service.ReagentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReagentServiceImpl implements ReagentService {

    private final ReagentRepository reagentRepository;

    @Override
    public ReagentResponseDto createReagent(ReagentCreateRequestDto dto) {
        log.info("Creating reagent: {}", dto.name());

        Reagent reagent = new Reagent();
        reagent.setId(UUID.randomUUID());
        reagent.setName(dto.name());
        reagent.setFormula(dto.formula());
        reagent.setCasNumber(dto.casNumber());
        reagent.setDescription(dto.description());
        reagent.setCreatedAt(LocalDate.now());
        reagent.setUpdatedAt(LocalDate.now());

        reagentRepository.save(reagent);

        log.info("Reagent created: {}", reagent.getId());

        return new ReagentResponseDto(
                reagent.getId(),
                reagent.getName(),
                reagent.getFormula(),
                reagent.getCasNumber(),
                reagent.getDescription()
        );
    }

    @Override
    public ReagentResponseDto getReagent(UUID id) {
        log.info("Loading reagent {}", id);
        Reagent reagent = reagentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Reagent not found: " + id));
        return new ReagentResponseDto(
                reagent.getId(),
                reagent.getName(),
                reagent.getFormula(),
                reagent.getCasNumber(),
                reagent.getDescription()
        );
    }

    @Override
    public List<ReagentResponseDto> getAllReagents() {
        log.info("Loading all reagents");

        return reagentRepository.findAll().stream()
                .map(reagent -> new ReagentResponseDto(
                        reagent.getId(),
                        reagent.getName(),
                        reagent.getFormula(),
                        reagent.getCasNumber(),
                        reagent.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public ReagentResponseDto updateReagent(UUID id, ReagentCreateRequestDto dto) {
        log.info("Updating reagent {}", id);

        Reagent reagent = reagentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Reagent not found"));
        reagent.setName(dto.name());
        reagent.setFormula(dto.formula());
        reagent.setCasNumber(dto.casNumber());
        reagent.setDescription(dto.description());
        reagent.setUpdatedAt(LocalDate.now());

        reagentRepository.save(reagent);

        return new ReagentResponseDto(
                reagent.getId(),
                reagent.getName(),
                reagent.getFormula(),
                reagent.getCasNumber(),
                reagent.getDescription()
        );
    }

    @Override
    public void deleteReagent(UUID id) {
        log.info("Deleting reagent {}", id);

        Reagent reagent = reagentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Reagent not found"));
        reagent.setActive(false);
        reagentRepository.save(reagent);
    }
}