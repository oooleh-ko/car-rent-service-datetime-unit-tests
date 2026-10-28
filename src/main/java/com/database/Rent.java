package com.database;

import java.time.Instant;
import java.time.LocalDateTime;

public class Rent {
    private Long id;
    private Long carId;
    private LocalDateTime startTime; // local time chosen by the client
    private LocalDateTime endTime;   // local time chosen by the client
    private Instant createdAt;       // moment the record was created

    // required: Spring creates the object from request JSON with it
    public Rent() { }

    public Rent(Long id, Long carId, LocalDateTime startTime,
                LocalDateTime endTime, Instant createdAt) {
        this.id = id;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}