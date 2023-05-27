package com.example.employeemanagment.services;

import com.example.employeemanagment.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {

    List<Employee> getAll();

    Employee findById(long id);

    ResponseEntity<Employee> saveEmployee(Employee employee);

    ResponseEntity deleteEmployee(long id);

    Employee getById(long id);

    Employee updateEmployee(long id, Employee employee);

}
