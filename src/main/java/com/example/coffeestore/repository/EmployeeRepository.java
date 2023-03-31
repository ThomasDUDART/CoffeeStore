package com.example.coffeestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coffeestore.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}