package com.autoworld.autoworld.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Job {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=8, message="Please Enter A Date")
    private String date;

    @NotNull
    @Size(min=3, max=50, message = "Enter a Task")
    private String task;

    @NotNull
    @Size(min=3, max=80)
    private String note;

    @ManyToOne
    private Tech tech;

    @ManyToOne
    private Car car;

    public Job(String date, String task, String note) {
        this.date = date;
        this.task = task;
        this.note = note;
    }

    public Job() {}

    public int getId() { return id; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTask() { return task; }

    public void setTask(String task) { this.task = task; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Tech getTech() { return tech; }

    public void setTech(Tech tech) { this.tech = tech; }

    public Car getCar() { return car; }

    public void setCar(Car car) { this.car = car; }

}
