package com.example.coffeestore.service;

import com.example.coffeestore.domain.beans;
import com.example.coffeestore.domain.*;
import com.example.coffeestore.repository.*;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrigineService {

    @Autowired
    private origineRepository repo;

    public List<origine> listAll(){
        return repo.findAll();
    }

    public void save(origine std){
        repo.save(std);
    }

    public origine get(long id){
        return repo.findById(id).get();
    }

    public void delete(long id){
        repo.deleteById(id);
    }
}
