package com.database;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Rent> rentRowMapper = (rs, rowNum) ->
            new Rent(rs.getLong("id"),
                    rs.getLong("car_id"),
                    rs.getObject("start_time", LocalDateTime.class),
                    rs.getObject("end_time", LocalDateTime.class),
                    rs.getObject("created_at", Instant.class));

    public RentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rent> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM car_rent ORDER BY start_time",
                rentRowMapper);
    }

    public List<Rent> findByCarId(Long carId) {
        return jdbcTemplate.query(
                "SELECT * FROM car_rent WHERE car_id = ?",
                rentRowMapper, carId);
    }

    public Rent save(Rent rent) {
        jdbcTemplate.update("INSERT INTO car_rent "
                        + "(car_id, start_time, end_time, created_at) "
                        + "VALUES (?, ?, ?, ?)",
                rent.getCarId(), rent.getStartTime(),
                rent.getEndTime(), rent.getCreatedAt());
        return rent;
    }
}
