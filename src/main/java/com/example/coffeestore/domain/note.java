package com.example.coffeestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
public class note {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
    private int noteId;

    @Column(name = "note")
    private String note;

    // Relation infini-infini avec la table notebeans
    @ManyToMany
    @JoinTable( name = "notebeans",
            joinColumns = @JoinColumn( name = "notes" ),
            inverseJoinColumns = @JoinColumn( name = "bean" ) )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Set<beans> bean;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
