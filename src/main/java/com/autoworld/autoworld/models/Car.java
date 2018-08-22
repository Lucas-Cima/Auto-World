package com.autoworld.autoworld.models;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Customer customer;

    @OneToMany
    @JoinColumn( name = "car_id")
    private List<Job> jobs = new ArrayList<>();

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

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Job> getJobs() { return jobs; }

    public void setJobs(List<Job> jobs) { this.jobs = jobs; }
}
