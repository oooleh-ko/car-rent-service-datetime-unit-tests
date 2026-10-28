package com.database;

public class Car {
    private Long id;
    private String model;
    private Integer maxSpeed;

    public Car() { }

    public Car(Long id, String model, Integer maxSpeed) {
        this.id = id;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(Integer maxSpeed) { this.maxSpeed = maxSpeed; }
}