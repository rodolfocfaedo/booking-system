package com.rodolfodev.bookingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfodev.bookingsystem.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Booking response returned by the API")
public record BookingResponseDTO(

        @Schema(description = "Booking UUID", example = "707589b7-5c22-4315-bc1d-103852806671")
        UUID id,

        @Schema(description = "Service name", example = "Haircut")
        String service,

        @Schema(description = "Booking start date and time", example = "2026-04-08T21:30:00")
        LocalDateTime startTime,

        @Schema(description = "Booking end date and time", example = "2026-04-08T22:30:00")
        LocalDateTime endTime,

        @Schema(description = "Client name", example = "Rodolfo")
        String clientName,

        @Schema(description = "Client phone number", example = "54999999999")
        String clientPhone,

        @Schema(description = "Booking status", example = "PENDING")
        BookingStatus status,

        @Schema(description = "Booking creation timestamp", example = "2026-04-08T18:35:10")
        LocalDateTime createdAt
) {
}
