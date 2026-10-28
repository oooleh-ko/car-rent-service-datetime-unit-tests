package com.database;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/rent")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public List<Rent> getAll() {
        return rentService.findAll();
    }

    @PostMapping
    public Rent create(@RequestBody Rent rent) {
        try {
            return rentService.create(rent);
        } catch (IllegalArgumentException e) {
            // invalid request data -> 400 Bad Request
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            // conflict with an existing booking -> 409 Conflict
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage());
        }
    }
}