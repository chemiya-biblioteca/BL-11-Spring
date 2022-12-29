package com.uva.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Car")
public class Car {
    @Id
    @GeneratedValue
    private int cid;

    @Column
    private String model;

    @Column
    private int horsepower;

    @Column
    private int year;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    Car(int cid,int year,String model,int horsepower,User user){
        this.user=user;
        this.year=year;
        this.cid=cid;
        this.model=model;
        this.horsepower=horsepower;
    }

    public Car(){

    }


}
