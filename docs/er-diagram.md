# Entity Relationship Overview

This document describes the initial relationship between the main entities of the Booking System.

---

## Main Entities

### User

Represents the person who creates bookings.

Main fields:

- id
- name
- email
- password
- created_at

---

### Resource

Represents something that can be reserved.

Examples:

- meeting room
- barber chair
- studio
- equipment

Main fields:

- id
- name
- type
- description

---

### Booking

Represents a reservation made by a user for a resource.

Main fields:

- id
- start_time
- end_time
- status
- user_id
- resource_id
- created_at

---

## Relationships

### User and Booking

One user can have many bookings.

User 1 → N Bookings

### Resource and Booking

One resource can have many bookings.

Resource 1 → N Bookings

---

## Notes

A booking must always be associated with:

- one valid user
- one valid resource

The system must prevent overlapping bookings for the same resource.
