package com.example.coffeestore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class beans {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int beansid;

    private String Nom;

    private int Score;

    private int Torrefaction;

    public beans() {
    }

    public beans(int beansid, String Nom, int Score, int Torrefaction) {
        this.beansid = beansid;
        this.Nom = Nom;
        this.Score = Score;
        this.Torrefaction = Torrefaction;
    }

    public int getId() {
        return beansid;
    }

    public void setId(int beansid) {
        this.beansid = beansid;
    }

    public String getEname() {
        return Nom;
    }

    public void setEname(String Nom) {
        this.Nom = Nom;
    }

    public int getMobile() {
        return Score;
    }

    public void setMobile(int Score) {
        this.Score = Score;
    }

    public int getSalary() {
        return Torrefaction;
    }

    public void setSalary(int Torrefaction) {
        this.Torrefaction = Torrefaction;
    }

    @Override
    public String toString() {
        return "Employee [id=" + beansid + ", ename=" + Nom + ", mobile=" + Score + ", salary=" + Torrefaction + "]";
    }
}