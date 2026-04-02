package com.rodolfodev.bookingsystem.infrastructure.repositories;

import com.rodolfodev.bookingsystem.infrastructure.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
