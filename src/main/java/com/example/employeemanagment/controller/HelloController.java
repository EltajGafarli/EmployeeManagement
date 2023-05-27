package com.example.employeemanagment.controller;

import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class HelloController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/")
    public String hello() {
        return "Hello, World";
    }

}
