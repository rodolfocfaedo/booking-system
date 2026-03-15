# API Design

This document describes the initial REST API design for the booking system.

---

## Bookings

### Create booking
POST /bookings

### List bookings
GET /bookings

### Get booking by id
GET /bookings/{id}

### Cancel booking
PATCH /bookings/{id}/cancel

---

## Resources

### Create resource
POST /resources

### List resources
GET /resources

### Get resource by id
GET /resources/{id}

---

## Users

### Create user
POST /users

### Get user by id
GET /users/{id}

---

## Future Endpoints

Authentication endpoints will be added later.

POST /auth/register  
POST /auth/login
