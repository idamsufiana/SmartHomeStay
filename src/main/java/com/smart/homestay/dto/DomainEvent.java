package com.smart.homestay.dto;

import java.time.Instant;

public interface DomainEvent {
    String eventType();
    Instant occurredAt();
}