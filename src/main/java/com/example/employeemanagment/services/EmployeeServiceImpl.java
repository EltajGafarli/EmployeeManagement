package com.example.employeemanagment.services;

import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.exceptions.EmployeeAlreadyExistException;
import com.example.employeemanagment.exceptions.EmployeeNotFoundException;
import com.example.employeemanagment.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Value("${employee.not.found-error}")
    private String employeeNotFoundErrorText;

    @Value("${employee.already.exist}")
    private String employeeAlreadyExist;
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        return employee.orElseThrow(() -> new EmployeeNotFoundException(this.employeeNotFoundErrorText + id));
    }

    @Override
    @Transactional
    public ResponseEntity<Employee> saveEmployee(Employee employee) {
        Optional<Employee> emplyeeById = this.employeeRepository.findById(employee.getId());
        if (emplyeeById.isPresent()) {
            throw new EmployeeAlreadyExistException(this.employeeAlreadyExist);
        }

        this.employeeRepository.save(employee);

        return ResponseEntity.ok(employee);
    }

    @Override
    @Transactional
    public ResponseEntity deleteEmployee(long id) {
        Optional<Employee> employeeById = this.employeeRepository.findById(id);
        employeeById.orElseThrow(() -> new EmployeeNotFoundException(this.employeeNotFoundErrorText + id));
        this.employeeRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public Employee getById(long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        return employee.orElseThrow(() -> new EmployeeNotFoundException(this.employeeNotFoundErrorText + id));
    }

    @Override
    @Transactional
    public Employee updateEmployee(long id, Employee employee) {
        Employee employeeById = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(this.employeeNotFoundErrorText));
        employeeById.setFirstName(employee.getFirstName());

        employeeById.setLastName(employee.getLastName());

        employeeById.setEmail(employee.getEmail());

        employeeById.setCompanyName(employee.getCompanyName());

        employeeById.setSalary(employee.getSalary());

        this.employeeRepository.save(employeeById);

        return employeeById;
    }


}
