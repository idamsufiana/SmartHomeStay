package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillStatus status;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal tax;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal total;
}
