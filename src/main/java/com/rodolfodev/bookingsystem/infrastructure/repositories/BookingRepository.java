package com.rodolfodev.bookingsystem.infrastructure.repositories;

import com.rodolfodev.bookingsystem.enums.BookingStatus;
import com.rodolfodev.bookingsystem.infrastructure.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("""
                SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
                FROM Booking b
                WHERE b.status <> :cancelledStatus
                  AND b.startTime < :endTime
                  AND b.endTime > :startTime
            """)
    boolean existsConflict(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("cancelledStatus") BookingStatus cancelledStatus
    );

    @Query("""
                SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
                FROM Booking b
                WHERE b.id <> :id
                  AND b.status <> :cancelledStatus
                  AND b.startTime < :endTime
                  AND b.endTime > :startTime
            """)
    boolean existsConflictExcludingCurrent(
            @Param("id") UUID id,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("cancelledStatus") BookingStatus cancelledStatus
    );
}
