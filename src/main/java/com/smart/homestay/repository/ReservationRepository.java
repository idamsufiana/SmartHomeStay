package com.smart.homestay.repository;

import com.smart.homestay.model.Facility;
import com.smart.homestay.model.Reservation;
import com.smart.homestay.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /* =========================
       BASIC QUERY
       ========================= */

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByRoomId(Long roomId);

    Optional<Reservation> findByIdAndUserId(Long id, Long userId);

    /* =========================
       DATE OVERLAP CHECK (CRITICAL)
       ========================= */

    /**
     * Check apakah room sudah dibooking pada range tanggal tertentu.
     *
     * Overlap rule (STANDARD):
     * newStart < existingEnd AND newEnd > existingStart
     */
    @Query("""
        SELECT COUNT(r) > 0
        FROM Reservation r
        WHERE r.roomId = :roomId
          AND r.status IN :activeStatuses
          AND :startDate < r.endDate
          AND :endDate > r.startDate
    """)
    boolean existsOverlappingReservation(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("activeStatuses") List<ReservationStatus> activeStatuses
    );

    /* =========================
       FIND ACTIVE RESERVATIONS
       ========================= */

    @Query("""
        SELECT r
        FROM Reservation r
        WHERE r.status IN :statuses
          AND r.startDate <= :date
          AND r.endDate > :date
    """)
    List<Reservation> findActiveReservationsOnDate(
            @Param("date") LocalDate date,
            @Param("statuses") List<ReservationStatus> statuses
    );

    /* =========================
       FOR BILLING / CHECKOUT
       ========================= */

    @Query("""
        SELECT r
        FROM Reservation r
        WHERE r.status = :status
          AND r.endDate = :date
    """)
    List<Reservation> findReservationsEndingOn(
            @Param("date") LocalDate date,
            @Param("status") ReservationStatus status
    );

    @Query("""
    SELECT COUNT(r) > 0
    FROM Reservation r
    WHERE r.roomId = :roomId
      AND :startDate < r.endDate
      AND :endDate > r.startDate
""")
    boolean existsOverlapping(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
