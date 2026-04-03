package com.rodolfodev.bookingsystem.service;

import com.rodolfodev.bookingsystem.dto.request.BookingRequestDTO;
import com.rodolfodev.bookingsystem.dto.response.BookingResponseDTO;
import com.rodolfodev.bookingsystem.enums.BookingStatus;
import com.rodolfodev.bookingsystem.infrastructure.entities.Booking;
import com.rodolfodev.bookingsystem.infrastructure.repositories.BookingRepository;
import com.rodolfodev.bookingsystem.mapper.BookingMapper;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingResponseDTO createBooking (BookingRequestDTO bookingRequestDTO){
        if (bookingRequestDTO.endTime().isBefore(bookingRequestDTO.startTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        Booking entity = bookingMapper.toEntity(bookingRequestDTO);
        entity.setStatus(BookingStatus.PENDING);
        return bookingMapper.toResponse(bookingRepository.save(entity));
    }
}
