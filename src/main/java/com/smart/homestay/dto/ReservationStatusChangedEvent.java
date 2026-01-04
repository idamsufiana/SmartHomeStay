package com.smart.homestay.dto;


import java.time.Instant;

public record ReservationStatusChangedEvent(Long reservationId, String status) implements DomainEvent {

    @Override
    public String eventType() {
        return "";
    }

    @Override public Instant occurredAt() { return Instant.now(); }
}