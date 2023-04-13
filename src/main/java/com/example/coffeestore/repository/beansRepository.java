package com.example.coffeestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeestore.domain.beans;

@Repository
public interface beansRepository extends JpaRepository<beans, Long> {

}