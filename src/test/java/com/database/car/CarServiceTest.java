package com.database.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.database.Car;
import com.database.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // changes in the database are rolled back after each test
@TestMethodOrder(MethodOrderer.MethodName.class)
class CarServiceTest {
    @Autowired
    CarService carService;

    @Test
    @DisplayName("Авто зі швидкістю 700 відхиляється")
    void test1Service() {
        Car car = new Car();
        car.setModel("Honda");
        car.setMaxSpeed(700);
        assertThrows(IllegalArgumentException.class,
                () -> carService.save(car));
    }

    @Test
    @DisplayName("Авто зі швидкістю 250 зберігається")
    void test2Service() {
        int before = carService.findAll().size();
        Car car = new Car();
        car.setModel("Honda");
        car.setMaxSpeed(250);
        carService.save(car);
        assertEquals(before + 1, carService.findAll().size(),
                "авто не збереглося");
    }

    @Test
    @DisplayName("Межа: авто зі швидкістю 500 зберігається")
    void test3Service() {
        int before = carService.findAll().size();
        Car car = new Car();
        car.setModel("Honda");
        car.setMaxSpeed(500);
        carService.save(car);
        assertEquals(before + 1, carService.findAll().size(),
                "авто з межовою швидкістю не збереглося");
    }

    // temporary: passes only if the other tests were rolled back
    @Test
    @DisplayName("Після інших тестів база порожня")
    void zzDatabaseIsEmptyAfterOtherTests() {
        assertEquals(0, carService.findAll().size());
    }
}