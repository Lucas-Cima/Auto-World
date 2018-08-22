package com.autoworld.autoworld.models;



import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=10, max=30, message="Please Enter a Full Name")
    private String name;

    @NotNull
    @Size(min=1, max=40, message="Please Enter an Address")
    private String address;

    @NotNull
    @Size(min=9, max=10, message="Please Enter a Phone Number")
    private String phone;

    @Email
    @Size(min=3, max=20, message = "Please Enter an E-mail")
    private String email;

    @OneToMany
    @JoinColumn( name = "customer_id")
    private List<Car> cars = new ArrayList<>();


    public Customer(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Customer() {}

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Car> getCars() { return cars; }

    public void setCars(List<Car> cars) { this.cars = cars; }
}
