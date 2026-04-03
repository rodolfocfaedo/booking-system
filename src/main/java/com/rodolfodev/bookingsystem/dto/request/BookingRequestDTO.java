package com.rodolfodev.bookingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.LocalDateTime;

public record BookingRequestDTO(

        @NotBlank
        String service,

        @Schema(example = "2026-04-03T15:30:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull
        LocalDateTime startTime,

        @Schema(example = "2026-04-03T16:00:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull
        LocalDateTime endTime,

        @NotBlank
        String clientName,

        @NotBlank
        String clientPhone

) {}
