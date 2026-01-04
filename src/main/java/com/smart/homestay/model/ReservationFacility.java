package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Builder
public class ReservationFacility {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Reservation reservation;

    @Column(nullable = false)
    private Long facilityId;

    @Column(nullable = false)
    private String facilityNameSnapshot;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private BigDecimal unitPrice;
}
