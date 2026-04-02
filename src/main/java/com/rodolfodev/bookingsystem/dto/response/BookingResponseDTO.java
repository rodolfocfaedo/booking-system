package com.rodolfodev.bookingsystem.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(UUID id, String service, LocalDateTime startTime,
                                 LocalDateTime endTime, String clientName,
                                 String clientPhone, LocalDateTime createdAt) {
}
