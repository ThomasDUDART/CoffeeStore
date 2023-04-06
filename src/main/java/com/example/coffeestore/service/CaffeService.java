package com.example.coffeestore.service;

import com.example.coffeestore.domain.*;
import com.example.coffeestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaffeService {

    @Autowired
    private beansRepository beanrepo;

    public List<beans> listAll(){
        return beanrepo.findAll();
    }

    public void save(beans std){
        beanrepo.save(std);
    }

    public beans get(long id){
        return beanrepo.findById(id).get();
    }

    public void delete(long id){
        beanrepo.deleteById(id);
    }
}
