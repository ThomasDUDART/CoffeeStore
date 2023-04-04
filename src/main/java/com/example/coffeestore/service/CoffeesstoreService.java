package com.example.coffeestore.service;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.repository.CoffeestoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeesstoreService {

    @Autowired
    private CoffeestoreRepository repo;

    public List<beans> listAll(){
        return repo.findAll();
    }

    public void save(beans std){
        repo.save(std);
    }

    public beans get(long id){
        return repo.findById(id).get();
    }

    public void delete(long id){
        repo.deleteById(id);
    }
}
