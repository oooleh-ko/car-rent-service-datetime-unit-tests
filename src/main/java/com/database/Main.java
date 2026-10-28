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
        jdbcTemplate.execute("DROP TABLE IF EXISTS car_rent");
        jdbcTemplate.execute("DROP TABLE IF EXISTS car");

        // table 'car' from lab work #9
        jdbcTemplate.execute("CREATE TABLE car("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "model VARCHAR(255), max_speed INT)");

        // new table of rents
        jdbcTemplate.execute("CREATE TABLE car_rent("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "car_id BIGINT REFERENCES car(id), "
                + "start_time TIMESTAMP, end_time TIMESTAMP, "
                + "created_at TIMESTAMP WITH TIME ZONE)");
    }
}