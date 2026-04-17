package com.rodolfodev.bookingsystem.controller;

import com.rodolfodev.bookingsystem.dto.BookingPatchDTO;
import com.rodolfodev.bookingsystem.dto.request.BookingRequestDTO;
import com.rodolfodev.bookingsystem.dto.response.BookingResponseDTO;
import com.rodolfodev.bookingsystem.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@Tag(name = "Bookings", description = "Endpoints for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(
            summary = "Create a new booking",
            description = """
                    Creates a new booking with a fixed duration of 1 hour.
                    
                    Rules:
                    - startTime is required
                    - endTime is calculated automatically by the backend
                    - overlapping bookings are not allowed
                    """
    )
    @ApiResponse(responseCode = "201", description = "Booking created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    @ApiResponse(responseCode = "409", description = "Time slot conflict")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody BookingRequestDTO bookingRequestDTO
    ) {
        BookingResponseDTO createdBooking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity
                .created(URI.create("/api/v1/bookings/" + createdBooking.id()))
                .body(createdBooking);
    }

    @Operation(
            summary = "Get booking by id",
            description = "Returns a single booking by its UUID"
    )
    @ApiResponse(responseCode = "200", description = "Booking found")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(
            @Parameter(description = "Booking UUID", example = "707589b7-5c22-4315-bc1d-103852806671")
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Operation(
            summary = "Get all bookings",
            description = """
                Retrieves all bookings from the system.
                
                This endpoint returns a list containing all registered bookings,
                including service, client information, booking time, status and creation date.
                """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Bookings retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(
            summary = "Partially update a booking",
            description = """
                    Updates only the fields sent in the request body.
                    
                    Important:
                    - Send only the fields you want to change
                    - Fields omitted from the request will remain unchanged
                    - If startTime is provided, endTime is automatically recalculated to +1 hour
                    - Do not send placeholder values such as "string" unless you really want to save them
                    """
    )
    @ApiResponse(responseCode = "200", description = "Booking updated successfully")
    @PatchMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> patchBookingById(
            @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Update only status",
                                            value = """
                                                    {
                                                      "status": "CONFIRMED"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Update only client name",
                                            value = """
                                                    {
                                                      "clientName": "Rodolfo"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Update only start time",
                                            value = """
                                                    {
                                                      "startTime": "2026-04-08T21:30:00"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @RequestBody BookingPatchDTO patchDTO
    ) {
        BookingResponseDTO updatedBooking = bookingService.patchBookingById(id, patchDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @Operation(
            summary = "Delete a booking",
            description = "Deletes a booking by its UUID"
    )
    @ApiResponse(responseCode = "204", description = "Booking deleted successfully")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(
            @Parameter(description = "Booking UUID", example = "707589b7-5c22-4315-bc1d-103852806671")
            @PathVariable UUID id
    ) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}
