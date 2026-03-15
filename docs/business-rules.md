# Business Rules

## Overview

The system allows users to create reservations for resources such as meeting rooms, barber chairs, studios, or other reservable assets.

The following rules define how bookings behave in the system.

---

## Booking Rules

- A booking cannot be created in the past.
- The end time must always be greater than the start time.
- A resource cannot have overlapping bookings.

### Example

Resource: Meeting Room A

Invalid bookings:

- Booking 1 → 10:00 to 11:00
- Booking 2 → 10:30 to 11:30

The second booking must be rejected.

---

## Booking Status

Bookings can have the following statuses:

- PENDING
- CONFIRMED
- CANCELLED

Only active bookings should block new reservations.

---

## Resource Rules

Resources represent entities that can be reserved.

Examples:

- meeting rooms
- barber chairs
- studios
- equipment

Each resource can have multiple bookings.

---

## User Rules

Users can create bookings.

A user may have multiple bookings.
