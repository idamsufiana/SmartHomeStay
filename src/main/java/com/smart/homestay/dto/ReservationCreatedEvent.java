package com.smart.homestay.dto;

import java.time.Instant;

public record ReservationCreatedEvent(
        Long reservationId,
        Long roomId,
        Long userId
) implements DomainEvent {

    @Override
    public String eventType() {
        return "ReservationCreated";
    }

    @Override
    public Instant occurredAt() {
        return Instant.now();
    }
}
