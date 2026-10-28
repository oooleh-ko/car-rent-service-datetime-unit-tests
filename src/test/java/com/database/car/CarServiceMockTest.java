package com.database.car;

import com.database.Car;
import com.database.CarRepository;
import com.database.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarServiceMockTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    void tooFastCarIsNotSavedToDatabase() {
        Car car = new Car();
        car.setModel("Honda");
        car.setMaxSpeed(700);
        assertThrows(IllegalArgumentException.class,
                () -> carService.save(car));
        verify(carRepository, never()).save(any());
    }
}