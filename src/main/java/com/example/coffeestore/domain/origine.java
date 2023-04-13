package com.example.coffeestore.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "origine")
public class origine {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
    private int origineId;

    @Column(name = "continent")
    private String continent;

    @Column(name = "pays")
    private String pays;
    @Column(name = "ferme")
    private String ferme;

    public origine(int id, String continent, String Pays, String Ferme)
    {
        this.origineId = id;
        this.continent = continent;
        this.ferme = Ferme;
    }

    public origine() {}

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
    @Override
    public String toString()
    {
        return pays+" en "+continent+"\n Ferme : "+ferme;
    }
}
