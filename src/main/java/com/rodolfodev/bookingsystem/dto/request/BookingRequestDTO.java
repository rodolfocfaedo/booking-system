package com.rodolfodev.bookingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.time.LocalDateTime;

public record BookingRequestDTO(@NotBlank String service,
                                @NonNull LocalDateTime startTime,
                                @NonNull LocalDateTime endTime,
                                @NotBlank String clientName,
                                @NotBlank String clientPhone)
{}
