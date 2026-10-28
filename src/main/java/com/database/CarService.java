package com.database;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        // business rule: speed must be from 1 to 500 km/h
        Integer speed = car.getMaxSpeed();
        if (speed == null || speed < 1 || speed > 500) {
            throw new IllegalArgumentException(
                    "Швидкість має бути від 1 до 500 км/год");
        }
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}