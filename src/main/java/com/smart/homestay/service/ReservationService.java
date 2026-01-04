package com.smart.homestay.service;

import com.smart.homestay.dto.CreateReservationRequest;
import com.smart.homestay.dto.ReservationCreatedEvent;
import com.smart.homestay.dto.ReservationStatusChangedEvent;
import com.smart.homestay.kafka.EventPublisher;
import com.smart.homestay.model.*;
import com.smart.homestay.repository.FacilityRepository;
import com.smart.homestay.repository.ReservationRepository;
import com.smart.homestay.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final FacilityRepository facilityRepository;
    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    public ReservationService(RoomRepository roomRepository, FacilityRepository facilityRepository, ReservationRepository reservationRepository, EventPublisher eventPublisher) {
        this.roomRepository = roomRepository;
        this.facilityRepository = facilityRepository;
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Reservation create(CreateReservationRequest req) {
        if (!req.startDate().isBefore(req.endDate())) {
            throw new IllegalArgumentException("startDate must be before endDate");
        }

        Room room = roomRepository.findById(req.roomId())
                .orElseThrow(() -> new IllegalArgumentException("room not found"));

        if (reservationRepository.existsOverlapping(room.getId(), req.startDate(), req.endDate())) {
            throw new IllegalStateException("Room not available on selected dates");
        }

        Reservation reservation = Reservation.builder()
                .userId(req.userId())
                .room(room)
                .startDate(req.startDate())
                .endDate(req.endDate())
                .status(ReservationStatus.CREATED)
                .build();

        if (req.facilities() != null) {
            for (var item : req.facilities()) {
                Facility f = facilityRepository.findById(item.facilityId())
                        .orElseThrow(() ->
                                new IllegalArgumentException("facility not found: " + item.facilityId()));

                reservation.getFacilities().add(
                        ReservationFacility.builder()
                                .reservation(reservation)
                                .facilityId(f.getId())
                                .qty(item.qty())
                                .unitPrice(f.getPrice()) // snapshot
                                .build()
                );
            }
        }


        Reservation saved = reservationRepository.save(reservation);

        eventPublisher.publish(new ReservationCreatedEvent(saved.getId(), saved.getUserId(), saved.getRoom().getId()));
        return saved;
    }

    @Transactional
    public Reservation checkIn(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("reservation not found"));

        if (r.getStatus() != ReservationStatus.CREATED && r.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Cannot check-in from status: " + r.getStatus());
        }

        r.setStatus(ReservationStatus.CHECKED_IN);
        eventPublisher.publish(new ReservationStatusChangedEvent(r.getId(), "CHECKED_IN"));
        return r;
    }

    @Transactional
    public Reservation checkOut(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("reservation not found"));

        if (r.getStatus() != ReservationStatus.CHECKED_IN) {
            throw new IllegalStateException("Cannot check-out from status: " + r.getStatus());
        }

        r.setStatus(ReservationStatus.CHECKED_OUT);
        eventPublisher.publish(new ReservationStatusChangedEvent(r.getId(), "CHECKED_OUT"));
        return r;
    }
}
