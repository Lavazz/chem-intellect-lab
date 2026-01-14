package com.chem.lab.reagentservice.service.kafka;

import com.chem.lab.reagentservice.event.InsufficientReagentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReagentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${reagent.kafka.topic-stock-out}")
    private String stockOutTopic;

    @Value("${reagent.kafka.topic-insufficient}")
    private String insufficientTopic;

    public void sendOutOfStockEvent(UUID reagentId) {
        log.info("Sending OUT_OF_STOCK event: {}", reagentId);
        kafkaTemplate.send(stockOutTopic, reagentId.toString());
    }

    public void sendInsufficientReagentEvent(UUID reagentId, double missing) {
        log.info("Sending INSUFFICIENT event: {} missing {}", reagentId, missing);

        InsufficientReagentEvent event =
                new InsufficientReagentEvent(reagentId, missing);

        kafkaTemplate.send(insufficientTopic, event);
    }
}