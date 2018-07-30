package com.autoworld.autoworld.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=15)
    private String make;

    @NotNull
    @Size(min=1, max=20)
    private String model;

    @NotNull
    @Size(min=4, max=4)
    private int year;


    public Car(String make, String model, Integer year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Car() {}

    public int getId() { return id; }

    public String getMake() { return make; }

    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

}
