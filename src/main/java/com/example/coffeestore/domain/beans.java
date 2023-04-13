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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "origine", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private origine origine;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "notebeans",
            joinColumns = @JoinColumn( name = "bean" ),
            inverseJoinColumns = @JoinColumn( name = "notes" ))
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

    public void setId(int id) {
        this.id = id;
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
    public Set<note> getNotes()
    {
        return notes;
    }
    public String getNotesToString() {
        String str = "";
        for(note n : notes)
            str = str+n.toString()+", ";
        return str;
    }

    public void setNotes(Set<note> notes) {
        this.notes = notes;
    }
    public void setNotes(note notes) {
        this.notes.add(notes);
    }
    @Override
    public String toString()
    {
        return Nom + "\n"+poids+"g, Score : "+Score+"/10, torréfaction : "+Torrefaction+"/100.\n"+qte+" unités en stock\n"+prix+"€";
    }

    public void setUnitePlusOne() {
        qte++;
    }

    public void setUniteMinusOne() {
        qte--;
    }

    public int getPrixTotal(){
        return prix*qte;
    }
    public int getPoidsTotal(){
        return poids*qte;
    }

}