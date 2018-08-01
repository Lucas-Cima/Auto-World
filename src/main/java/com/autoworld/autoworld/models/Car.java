package com.autoworld.autoworld.models;


import org.hibernate.validator.constraints.Range;

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
    @Size(min=1, max=15, message = "Please Enter a Make")
    private String make;

    @NotNull
    @Size(min=1, max=20, message = "Please Enter a Model")
    private String model;

    @NotNull
    @Range(min=1940, max=2018, message = "Please Enter a Year")
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
