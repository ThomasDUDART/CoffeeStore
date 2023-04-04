package com.example.coffeestore.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "beans")
public class beans {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int beansid;

    @Column(name = "Nom")
    private String Nom;
    @Column(name = "Score")
    private int Score;
    @Column(name = "Torrefaction")
    private int Torrefaction;
    @Column(name = "PoidsUnitaire")
    private int poids;
    @Column(name = "PrixUnitaire")
    private int prix;



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
        return "Café " + Nom + ",\n Score=" + Score + ",\n Torrefaction=" + Torrefaction;
    }
}