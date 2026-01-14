package com.chem.lab.reagentservice.service;

import com.chem.lab.reagentservice.dto.request.ReagentConsumptionRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentConsumptionResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReagentConsumptionService {
    void registerConsumption(ReagentConsumptionRequestDto dto);
    List<ReagentConsumptionResponseDto> getByReagent(UUID reagentId);
    List<ReagentConsumptionResponseDto> getByExperiment(UUID experimentId);
}
