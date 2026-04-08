package com.rodolfodev.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfodev.bookingsystem.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Partial booking update. Send only the fields you want to change.")
public record BookingPatchDTO(

        @Schema(description = "Service name. Omit if unchanged.", example = "Haircut")
        String service,

        @Schema(
                description = "New booking start date and time. If sent, endTime is recalculated automatically to +1 hour.",
                example = "2026-04-08T21:30:00"
        )
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startTime,

        @Schema(description = "Client name. Omit if unchanged.", example = "Rodolfo")
        String clientName,

        @Schema(description = "Client phone number. Omit if unchanged.", example = "54999999999")
        String clientPhone,

        @Schema(description = "Booking status", example = "CONFIRMED")
        BookingStatus status
) {
}
