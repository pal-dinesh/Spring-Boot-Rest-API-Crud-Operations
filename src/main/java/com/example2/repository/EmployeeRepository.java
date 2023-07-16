package com.example2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example2.model.employee;

public interface EmployeeRepository extends JpaRepository <employee, Long> {

}
