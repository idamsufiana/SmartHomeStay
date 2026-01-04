package com.smart.homestay.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "user_accounts",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email", unique = true)
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Business identifier
     * Digunakan lintas microservice (reservation, billing, dsb)
     */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String fullName;

    /**
     * Optional:
     * Disimpan hanya jika service ini handle auth sendiri
     * Kalau pakai Auth0 / Keycloak → field ini bisa dihapus
     */
    @Column(length = 255)
    private String passwordHash;

    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        this.active = true;
        this.createdAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
