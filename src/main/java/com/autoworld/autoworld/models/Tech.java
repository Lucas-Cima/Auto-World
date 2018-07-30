package com.autoworld.autoworld.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tech {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    public Tech(String name) {
        this.name = name;
    }

    public Tech() {}

    public int getId() { return id; }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
