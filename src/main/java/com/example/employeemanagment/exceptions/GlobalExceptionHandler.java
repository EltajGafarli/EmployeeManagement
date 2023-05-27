package com.example.employeemanagment.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public List<Map<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return exception
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, Object> errors = new HashMap<>();
                    String field = error.getField();
                    Object rejectedValue = error.getRejectedValue();

                    errors.put(field, rejectedValue);
                    return errors;
                }).toList();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String employeeNotFound(EmployeeNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public String employeeAlreadyExist(EmployeeAlreadyExistException exception) {
        return exception.getMessage();
    }
}
