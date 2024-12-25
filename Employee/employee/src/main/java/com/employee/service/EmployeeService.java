package com.employee.service;

import java.util.List;

import com.employee.dao.Employee;
import com.employee.dto.EmployeeDto;

public interface EmployeeService {
  Employee createUser(Employee employee);

  List<EmployeeDto> getAllUsers();

  EmployeeDto getEmployeeById(Long employeeId);

  boolean deleteUser(Long employeeId);

  Employee updateUser(Long employeeId, Employee employee);
}
