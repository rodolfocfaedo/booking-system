package com.rodolfodev.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodolfodev.bookingsystem.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record BookingPatchDTO(String service,
                              @Schema(
                                      example = "2026-04-08T21:39:30",
                                      type = "string",
                                      format = "date-time"
                              )
                              @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                              LocalDateTime startTime,
                              String clientName,
                              String clientPhone,
                              BookingStatus status)
{
}
