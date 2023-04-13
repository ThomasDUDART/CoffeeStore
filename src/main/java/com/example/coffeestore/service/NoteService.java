package com.example.coffeestore.service;

import com.example.coffeestore.domain.*;
import com.example.coffeestore.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private noteRepository repo;

    public List<note> listAll(){
        return repo.findAll();
    }

    public void save(note std){
        repo.save(std);
    }

    public note get(long id){
        return repo.findById(id).get();
    }

    public void delete(long id){
        repo.deleteById(id);
    }
}
