package com.example.coffeestore.domain;

import jakarta.persistence.*;

@Entity
public class notebeans {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int beansId;

    private int noteId;
}
