package com.smart.homestay.kafka;

import com.smart.homestay.dto.DomainEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${app.kafka.topic:homestay.events}")
    private String topic;

    public EventPublisher(KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(DomainEvent event) {
        kafkaTemplate.send(topic, event.type(), event);
    }
}