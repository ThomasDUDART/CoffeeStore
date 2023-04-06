package com.example.coffeestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@Entity
@Table(name = "beans")
public class beans {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String Nom;
    @Column(name = "score")
    private int Score;
    @Column(name = "torrefaction")
    private int Torrefaction;
    @Column(name = "pu")
    private int poids;
    @Column(name = "prix")
    private int prix;

    @Column(name = "quantite")
    private int qte;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origine", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private origine origine;

    @ManyToMany
    @JoinTable( name = "notebeans",
            joinColumns = @JoinColumn( name = "bean" ),
            inverseJoinColumns = @JoinColumn( name = "notes" ) )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Set<note> notes;

    public beans(){}

    public beans(int id, String nom, int score, int torrefaction, int poids, int prix, int qte, com.example.coffeestore.domain.origine origine) {
        this.id = id;
        Nom = nom;
        Score = score;
        Torrefaction = torrefaction;
        this.poids = poids;
        this.prix = prix;
        this.qte = qte;
        this.origine = origine;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getTorrefaction() {
        return Torrefaction;
    }

    public void setTorrefaction(int torrefaction) {
        Torrefaction = torrefaction;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public com.example.coffeestore.domain.origine getOrigine() {
        return origine;
    }

    public void setOrigine(com.example.coffeestore.domain.origine origine) {
        this.origine = origine;
    }
}



