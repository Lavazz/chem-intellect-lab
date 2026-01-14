package com.chem.lab.reagentservice.service;

import com.chem.lab.reagentservice.dto.request.ReagentCreateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReagentService {
    ReagentResponseDto createReagent(ReagentCreateRequestDto request);
    ReagentResponseDto getReagent(UUID id);
    List<ReagentResponseDto> getAllReagents();
    ReagentResponseDto updateReagent(UUID id, ReagentCreateRequestDto request);
    void deleteReagent(UUID id);
}
