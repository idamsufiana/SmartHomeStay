package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Facility {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // BREAKFAST, EXTRA_BED

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price; // simple: flat per night or flat overall (kita treat "per night" di billing)
}
