package com.rodolfodev.bookingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(UUID id,

                                 String service,

                                 @Schema(example = "2026-04-03T15:30:00")
                                 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                 LocalDateTime startTime,

                                 @Schema(example = "2026-04-03T16:00:00")
                                 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                 LocalDateTime endTime,

                                 String clientName,

                                 String clientPhone,

                                 LocalDateTime createdAt) {
}
