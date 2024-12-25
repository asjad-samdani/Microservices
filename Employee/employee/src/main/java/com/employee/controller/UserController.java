package com.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Exception.UserNotFoundException;
import com.employee.dao.Employee;
import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

@RestController
public class UserController {
  @Autowired
  private EmployeeService userService;

  // get user by id
  @GetMapping("/employees/{employeeId}")
  public ResponseEntity<?> getEmployeeDetailsById(@PathVariable Long employeeId) {
    try {
      EmployeeDto user = userService.getEmployeeById(employeeId);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (UserNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
  }

  // get all users
  @GetMapping("/getAllEmployee")
  public ResponseEntity<?> getAllUserFromEntity() {
    List<EmployeeDto> allUser = userService.getAllUsers();
    if (!allUser.isEmpty()) {
      return new ResponseEntity<>(allUser, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest().body("No user found");
    }
  }

  @PostMapping("/createUser")
  public ResponseEntity<?> createUser(@RequestBody Employee user) {
    Employee entity = userService.createUser(user);
    if (entity != null) {
      return new ResponseEntity<>(entity, HttpStatus.CREATED);
    } else {
      return ResponseEntity.badRequest().body("User not created");
    }
  }

  @PutMapping("updateUserById/{id}")
  public ResponseEntity<?> updateUserEntity(@PathVariable Long id, @RequestBody Employee user) {
    try {
      Employee updatedUser = userService.updateUser(id, user);
      if (updatedUser != null) {
        return new ResponseEntity<>(Map.of("message", "Update successful"), HttpStatus.OK);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
      }
    } catch (UserNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
  }

  @DeleteMapping("/deleteUser/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    boolean isDeleted = userService.deleteUser(id);
    if (isDeleted) {
      return new ResponseEntity<>(Map.of("message:", "User delete Successfully"), HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message:", "User not found"));
    }
  }

}
