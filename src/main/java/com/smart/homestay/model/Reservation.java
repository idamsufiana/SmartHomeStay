package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(optional = false)
    private Room room;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate; // exclusive (checkout date)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    /* =========================
       FACILITIES (JOIN ENTITY)
       ========================= */
    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReservationFacility> facilities = new ArrayList<>();

    /* =========================
       HELPER METHOD (IMPORTANT)
       ========================= */
    public void addFacility(ReservationFacility rf) {
        rf.setReservation(this);
        this.facilities.add(rf);
    }

    public void removeFacility(ReservationFacility rf) {
        rf.setReservation(null);
        this.facilities.remove(rf);
    }
}
