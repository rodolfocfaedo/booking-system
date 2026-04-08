package com.rodolfodev.bookingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Request body used to create a booking")
public record BookingRequestDTO(

        @Schema(description = "Service name", example = "Haircut")
        @NotBlank
        String service,

        @Schema(description = "Booking start date and time", example = "2026-04-08T21:30:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull
        LocalDateTime startTime,

        @Schema(description = "Client name", example = "Rodolfo")
        @NotBlank
        String clientName,

        @Schema(description = "Client phone number", example = "54999999999")
        @NotBlank
        String clientPhone
) {
}

