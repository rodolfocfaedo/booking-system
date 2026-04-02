package com.rodolfodev.bookingsystem.mapper;

import com.rodolfodev.bookingsystem.dto.request.BookingRequestDTO;
import com.rodolfodev.bookingsystem.dto.response.BookingResponseDTO;
import com.rodolfodev.bookingsystem.infrastructure.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    Booking toEntity(BookingRequestDTO dto);

    BookingResponseDTO toResponse(Booking booking);


}
