# Database Model

This document describes the initial database structure.

The system uses PostgreSQL.

---

## users

Fields:

- id
- name
- email
- password
- created_at

---

## resources

Fields:

- id
- name
- type
- description

Examples:

- Meeting Room A
- Barber Chair 1
- Studio Room

---

## bookings

Fields:

- id
- start_time
- end_time
- status
- user_id
- resource_id
- created_at

---

## Relationships

- User 1 → N Bookings
- Resource 1 → N Bookings
