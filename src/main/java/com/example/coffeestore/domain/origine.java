package com.example.coffeestore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class origine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int origineId;

    private String continent;
    private String pays;
    private String ferme;

    public int getOrigineId() {
        return origineId;
    }

    public void setOrigineId(int origineId) {
        this.origineId = origineId;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getFerme() {
        return ferme;
    }

    public void setFerme(String ferme) {
        this.ferme = ferme;
    }
}
