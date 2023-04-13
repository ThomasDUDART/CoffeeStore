package com.example.coffeestore.repository;

import com.example.coffeestore.domain.note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface noteRepository extends JpaRepository<note, Long> {
}
