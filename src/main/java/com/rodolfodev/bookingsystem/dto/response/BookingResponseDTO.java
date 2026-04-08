package com.rodolfodev.bookingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(UUID id,

                                 String service,

                                 @Schema(
                                         example = "2026-04-08T21:39:30",
                                         type = "string",
                                         format = "date-time"
                                 )
                                 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                 LocalDateTime startTime,

                                 @Schema(
                                         example = "2026-04-08T21:39:30",
                                         type = "string",
                                         format = "date-time"
                                 )
                                 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                 LocalDateTime endTime,

                                 String clientName,

                                 String clientPhone,

                                 LocalDateTime createdAt) {
}
