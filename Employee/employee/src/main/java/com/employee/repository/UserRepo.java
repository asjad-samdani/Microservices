package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.dao.Employee;

public interface UserRepo extends JpaRepository<Employee, Long> {

}
