package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private boolean active = true;
}
