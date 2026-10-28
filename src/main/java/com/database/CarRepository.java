package com.database;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Car> carRowMapper = (rs, rowNum) ->
            new Car(rs.getLong("id"), rs.getString("model"),
                    rs.getInt("max_speed"));

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> findAll() {
        return jdbcTemplate.query("SELECT * FROM car", carRowMapper);
    }

    public Car save(Car car) {
        jdbcTemplate.update(
                "INSERT INTO car (model, max_speed) VALUES (?, ?)",
                car.getModel(), car.getMaxSpeed());
        return car;
    }
}