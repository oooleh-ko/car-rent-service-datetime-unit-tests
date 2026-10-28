package com.database;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final Clock clock;

    public RentService(RentRepository rentRepository,
                       Clock clock) {
        this.rentRepository = rentRepository;
        this.clock = clock;
    }

    public Rent create(Rent rent) {
        LocalDateTime start = rent.getStartTime();
        LocalDateTime end = rent.getEndTime();

        // rule 1: return time must be after pick-up time
        if (start == null || end == null
                || !start.isBefore(end)) {
            throw new IllegalArgumentException(
                    "Час повернення має бути пізніше "
                            + "за час видачі");
        }
        // rule 2: cannot book a time that has already passed
        if (start.isBefore(LocalDateTime.now(clock))) {
            throw new IllegalArgumentException(
                    "Не можна бронювати на час, що вже минув");
        }
        // rule 3: a rent lasts at least 1 hour
        Duration duration = Duration.between(start, end);
        if (duration.compareTo(Duration.ofHours(1)) < 0) {
            throw new IllegalArgumentException(
                    "Мінімальний час оренди - 1 година");
        }
        // rule 4: a car cannot be rented to two clients
        // at the same time
        List<Rent> rents =
                rentRepository.findByCarId(rent.getCarId());
        for (Rent existing : rents) {
            if (overlaps(rent, existing)) {
                throw new IllegalStateException(
                        "Авто вже заброньоване на цей час");
            }
        }
        // creation time is set by the server, not the client
        rent.setCreatedAt(Instant.now(clock));
        return rentRepository.save(rent);
    }

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    // intervals [start, end) overlap if each one
    // starts before the other one ends
    static boolean overlaps(Rent a, Rent b) {
        return a.getStartTime().isBefore(b.getEndTime())
                && b.getStartTime().isBefore(a.getEndTime());
    }
}