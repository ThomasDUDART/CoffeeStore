package com.example.coffeestore.domain;

import jakarta.persistence.*;


@Entity
public class beans {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int beansid;
    private String Nom;
    private int Score;
    private int Torrefaction;

    private int Poidsunitaire,Unite;

    private float Prixunitaire;


    public beans() {
    }

    public beans(int beansid, String Nom, int Poidsunitaire, float Prixunitaire, int Unite, int Score, int Torrefaction) {
        this.beansid = beansid;
        this.Nom = Nom;
        this.Score = Score;
        this.Torrefaction = Torrefaction;
        this.Prixunitaire = Prixunitaire;
        this.Poidsunitaire = Poidsunitaire;
        this.Unite =Unite;
    }

    public int getId() {
        return beansid;
    }

    public void setId(int beansid) {
        this.beansid = beansid;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public int getTorrefaction() {
        return Torrefaction;
    }

    public void setTorrefaction(int Torrefaction) {
        this.Torrefaction = Torrefaction;
    }

    public float getPrixUnitaire() {
        return Prixunitaire;
    }

    public void setPoidsUnitaire(int poidsunitaire) {
        Poidsunitaire = poidsunitaire;
    }

    public int getPoidsUnitaire() {
        return Poidsunitaire;
    }


    public void setPrixUnitaire(float prixunitaire) {
        Prixunitaire = prixunitaire;
    }

    public int getUnite() {
        return Unite;
    }

    public void setUnite(int unite) {
        Unite = unite;
    }

    @Override
    public String toString() {
        return "beans [beansid=" + beansid + ", Poidsunitaire=" + Poidsunitaire + ", Prixunitaire=" + Prixunitaire + ", Unite=" + Unite + ", Nom=" + Nom + ", Score=" + Score + ", Torrefaction=" + Torrefaction + "]";
    }
}