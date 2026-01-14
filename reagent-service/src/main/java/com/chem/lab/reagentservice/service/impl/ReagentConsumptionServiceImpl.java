package com.chem.lab.reagentservice.service.impl;

import com.chem.lab.reagentservice.dto.request.ReagentConsumptionRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentConsumptionResponseDto;
import com.chem.lab.reagentservice.entity.ReagentConsumption;
import com.chem.lab.reagentservice.repository.ReagentConsumptionRepository;
import com.chem.lab.reagentservice.service.ReagentConsumptionService;
import com.chem.lab.reagentservice.service.ReagentStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReagentConsumptionServiceImpl implements ReagentConsumptionService {

    private final ReagentStockService stockService;
    private final ReagentConsumptionRepository consumptionRepository;

    @Override
    public void registerConsumption(ReagentConsumptionRequestDto dto) {
        log.info("Registering consumption of reagent {}", dto.reagentId());

        stockService.consume(dto.reagentId(), dto.amount());

        ReagentConsumption consumption = new ReagentConsumption();
        consumption.setId(UUID.randomUUID());
        consumption.setReagentId(dto.reagentId());
        consumption.setAmount(dto.amount());
        consumption.setUnit(dto.unit());
        consumption.setExperimentId(dto.experimentId());
        consumption.setConsumedAt(LocalDateTime.now());

        consumptionRepository.save(consumption);

        log.info("Consumption registered: {}", consumption.getId());
    }

    @Override
    public List<ReagentConsumptionResponseDto> getByReagent(UUID reagentId) {
        log.info("Fetching consumptions for reagent {}", reagentId);

        List<ReagentConsumption> consumptions = consumptionRepository.findByReagentId(reagentId);

        return consumptions.stream()
                .map(consumption -> new ReagentConsumptionResponseDto(
                        consumption.getId(),
                        consumption.getReagentId(),
                        consumption.getAmount(),
                        consumption.getUnit(),
                        consumption.getExperimentId(),
                        consumption.getConsumedAt()
                ))
                .toList();
    }

    @Override
    public List<ReagentConsumptionResponseDto> getByExperiment(UUID experimentId) {
        log.info("Fetching consumptions for experiment {}", experimentId);

        List<ReagentConsumption> consumptions = consumptionRepository.findByExperimentId(experimentId);

        return consumptions.stream()
                .map(consumption -> new ReagentConsumptionResponseDto(
                        consumption.getId(),
                        consumption.getReagentId(),
                        consumption.getAmount(),
                        consumption.getUnit(),
                        consumption.getExperimentId(),
                        consumption.getConsumedAt()
                ))
                .toList();
    }
}
