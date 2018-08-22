package com.autoworld.autoworld.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tech {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25 ,message = "Please Enter Full Name")
    private String name;

    @OneToMany
    @JoinColumn( name = "tech_id")
    private List<Job> jobs = new ArrayList<>();

    public Tech(String name) {
        this.name = name;
    }

    public Tech() {}

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Job> getJobs() { return jobs; }

    public void setJobs(List<Job> jobs) { this.jobs = jobs; }
}
