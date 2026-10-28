package com.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        runJDBC();
    }

    void runJDBC() {
        // table 'car' from lab work #9
        jdbcTemplate.execute("DROP TABLE car IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE car(id SERIAL, "
                + "model VARCHAR(255), max_speed INT)");

        // new table of rents
        jdbcTemplate.execute("DROP TABLE car_rent IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE car_rent(id SERIAL, "
                + "car_id BIGINT, "
                + "start_time TIMESTAMP, end_time TIMESTAMP, "
                + "created_at TIMESTAMP WITH TIME ZONE)");
    }
}