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

    public note(int noteId, String note, Set<beans> bean) {
        this.noteId = noteId;
        this.note = note;
        this.bean = bean;
    }
    public note(){}

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<beans> getBean() {
        return bean;
    }


    public void setBean(Set<beans> bean) {
        this.bean = bean;
    }

    public void setBean(beans bean) {
        this.bean.add(bean);
    }
    @Override
    public String toString()
    {
        return note;
    }
}
