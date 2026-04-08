package com.rodolfodev.bookingsystem.controller;

import com.rodolfodev.bookingsystem.dto.BookingPatchDTO;
import com.rodolfodev.bookingsystem.dto.request.BookingRequestDTO;
import com.rodolfodev.bookingsystem.dto.response.BookingResponseDTO;
import com.rodolfodev.bookingsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO booking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity
                .created(URI.create("/bookings/" + id))
                .body(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable UUID id) {
        BookingResponseDTO bookingById = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingById);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> patchBookingById(@PathVariable UUID id, @RequestBody BookingPatchDTO patchDTO) {
        BookingResponseDTO updatedBooking = bookingService.patchBookingById(id, patchDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable UUID id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}
