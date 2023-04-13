package com.example.coffeestore.repository;

import com.example.coffeestore.domain.origine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface origineRepository extends JpaRepository<origine, Long> {
}
