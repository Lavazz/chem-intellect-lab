package com.chem.lab.reagentservice.service.impl;

import com.chem.lab.reagentservice.dto.request.ReagentStockUpdateRequestDto;
import com.chem.lab.reagentservice.dto.response.ReagentAvailabilityResponseDto;
import com.chem.lab.reagentservice.dto.response.ReagentStockResponseDto;
import com.chem.lab.reagentservice.entity.ReagentStock;
import com.chem.lab.reagentservice.repository.ReagentStockRepository;
import com.chem.lab.reagentservice.service.ReagentStockService;
import com.chem.lab.reagentservice.service.kafka.ReagentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReagentStockServiceImpl implements ReagentStockService {

    private static final String GRAM = "g";
    private final ReagentStockRepository stockRepository;
    private final ReagentEventProducer kafkaProducer;

    @Override
    @Transactional
    public void addStock(ReagentStockUpdateRequestDto dto) {
        log.info("Adding stock for reagent {}", dto.reagentId());

        ReagentStock stock = new ReagentStock();
        stock.setId(UUID.randomUUID());
        stock.setReagentId(dto.reagentId());
        stock.setQuantity(dto.quantity());
        stock.setUnit(dto.unit());
        stock.setReceivedAt(LocalDate.now());
        stock.setActive(true);

        stockRepository.save(stock);

        log.info("Stock added: {}", stock.getId());
    }

    @Override
    public List<ReagentStockResponseDto> getStock(UUID reagentId) {
        log.info("Getting stock for reagent {}", reagentId);
        return stockRepository.findByReagentId(reagentId).stream()
                .map(stock -> new ReagentStockResponseDto(
                        stock.getId(),
                        stock.getReagentId(),
                        stock.getQuantity(),
                        stock.getUnit(),
                        stock.getReceivedAt(),
                        stock.getActive()))
                .toList();
    }

    @Override
    public ReagentAvailabilityResponseDto checkAvailability(UUID reagentId, Double required) {
        log.info("Checking availability for reagent {}", reagentId);

        Double total = stockRepository.sumQuantityByReagentId(reagentId);

        boolean enough = total != null && total >= required;

        return new ReagentAvailabilityResponseDto(
                reagentId,
                total == null ? 0.0 : total,
                GRAM,
                enough
        );
    }

    @Override
    @Transactional
    public void consume(UUID reagentId, Double amount) {
        log.info("Consuming {} units of reagent {}", amount, reagentId);

        List<ReagentStock> stocks = stockRepository.findActiveByReagentIdOrderByReceivedAt(reagentId);

        double remainingAmount = amount;

        for (ReagentStock stock : stocks) {
            if (remainingAmount <= 0) {
                break;
            }

            if (stock.getQuantity() >= remainingAmount) {
                stock.setQuantity(stock.getQuantity() - remainingAmount);
                remainingAmount = 0;
            } else {
                remainingAmount -= stock.getQuantity();
                stock.setQuantity(0.0);
                stock.setActive(false);

                kafkaProducer.sendOutOfStockEvent(reagentId);
            }

            stockRepository.save(stock);
        }

        if (remainingAmount > 0) {
            log.error("Not enough stock available for reagent {}", reagentId);
            throw new IllegalStateException("Not enough reagent stock available for consumption");
        }

        log.info("Reagent consumption completed successfully for reagent {}", reagentId);
    }
}