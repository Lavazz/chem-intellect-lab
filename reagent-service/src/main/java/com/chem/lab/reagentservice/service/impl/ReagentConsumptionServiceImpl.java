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
import java.util.stream.Collectors;

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

        // Получаем все записи расхода для данного реагента
        List<ReagentConsumption> consumptions = consumptionRepository.findByReagentId(reagentId);

        // Преобразуем их в DTO
        return consumptions.stream()
                .map(consumption -> new ReagentConsumptionResponseDto(
                        consumption.getId(),
                        consumption.getReagentId(),
                        consumption.getAmount(),
                        consumption.getUnit(),
                        consumption.getExperimentId(),
                        consumption.getConsumedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReagentConsumptionResponseDto> getByExperiment(UUID experimentId) {
        log.info("Fetching consumptions for experiment {}", experimentId);

        // Получаем все записи расхода для данного эксперимента
        List<ReagentConsumption> consumptions = consumptionRepository.findByExperimentId(experimentId);

        // Преобразуем их в DTO
        return consumptions.stream()
                .map(consumption -> new ReagentConsumptionResponseDto(
                        consumption.getId(),
                        consumption.getReagentId(),
                        consumption.getAmount(),
                        consumption.getUnit(),
                        consumption.getExperimentId(),
                        consumption.getConsumedAt()
                ))
                .collect(Collectors.toList());
    }
}
