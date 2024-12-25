package com.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Exception.UserNotFoundException;
import com.employee.dao.Employee;
import com.employee.dto.EmployeeDto;
import com.employee.repository.UserRepo;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public Employee createUser(Employee user) {
    return userRepo.save(user);

  }

  @Override
  public List<EmployeeDto> getAllUsers() {
    List<Employee> users = userRepo.findAll();
    return users.stream()
        .map(user -> objectMapper.convertValue(user, EmployeeDto.class)).toList();
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = userRepo.findById(employeeId)
        .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + employeeId));
    return objectMapper.convertValue(employee, EmployeeDto.class);
  }

  @Override
  public boolean deleteUser(Long employeeId) {
    if (userRepo.existsById(employeeId)) {
      userRepo.deleteById(employeeId);
      return true;

    }
    return false;

  }

  @Override
  public Employee updateUser(Long employeeId, Employee employee) {
    if (userRepo.existsById(employeeId)) {
      employee.setEmployeeId(employeeId);
      return userRepo.save(employee);
    } else {
      throw new UserNotFoundException("User not found with ID: " + employeeId);
    }

  }

}