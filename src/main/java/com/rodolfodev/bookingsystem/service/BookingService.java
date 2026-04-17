package com.rodolfodev.bookingsystem.service;

import com.rodolfodev.bookingsystem.dto.BookingPatchDTO;
import com.rodolfodev.bookingsystem.dto.request.BookingRequestDTO;
import com.rodolfodev.bookingsystem.dto.response.BookingResponseDTO;
import com.rodolfodev.bookingsystem.enums.BookingStatus;
import com.rodolfodev.bookingsystem.exceptions.BookingConflictException;
import com.rodolfodev.bookingsystem.exceptions.BookingNotFoundException;
import com.rodolfodev.bookingsystem.infrastructure.entities.Booking;
import com.rodolfodev.bookingsystem.infrastructure.repositories.BookingRepository;
import com.rodolfodev.bookingsystem.mapper.BookingMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    // CREATE a booking

    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        LocalDateTime startTime = bookingRequestDTO.startTime();
        LocalDateTime endTime = startTime.plusHours(1);
        boolean hasConflict = bookingRepository.existsConflict(
                startTime,
                endTime,
                BookingStatus.CANCELLED
        );
        if (hasConflict) {
            throw new BookingConflictException("This time slot is already booked");
        }
        Booking entity = bookingMapper.toEntity(bookingRequestDTO);
        entity.setStartTime(startTime);
        entity.setEndTime(endTime);
        entity.setStatus(BookingStatus.PENDING);
        return bookingMapper.toResponse(bookingRepository.save(entity));
    }

    // GET booking by id

    public BookingResponseDTO getBookingById(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new BookingNotFoundException("Id not found: " + id));
        return bookingMapper.toResponse(booking);
    }

    // GET all bookings

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    // PATCH partially a booking by their id

    public BookingResponseDTO patchBookingById(UUID id, BookingPatchDTO patchDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
        if (patchDTO.service() != null && !patchDTO.service().isBlank()) {
            booking.setService(patchDTO.service());
        }
        if (patchDTO.clientName() != null && !patchDTO.clientName().isBlank()) {
            booking.setClientName(patchDTO.clientName());
        }
        if (patchDTO.clientPhone() != null && !patchDTO.clientPhone().isBlank()) {
            booking.setClientPhone(patchDTO.clientPhone());
        }
        if (patchDTO.status() != null) {
            booking.setStatus(patchDTO.status());
        }
        if (patchDTO.startTime() != null) {
            LocalDateTime newStartTime = patchDTO.startTime();
            LocalDateTime newEndTime = newStartTime.plusHours(1);
            boolean hasConflict = bookingRepository.existsConflictExcludingCurrent(
                    booking.getId(),
                    newStartTime,
                    newEndTime,
                    BookingStatus.CANCELLED
            );
            if (hasConflict) {
                throw new BookingConflictException("This time slot is already booked");
            }
            booking.setStartTime(newStartTime);
            booking.setEndTime(newEndTime);
        }
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponse(updatedBooking);
    }

    // DELETE booking by their id

    public void deleteBookingById(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        bookingRepository.delete(booking);
    }
}
