package com.example.employeemanagment.controller;

import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/")
    public String home(){
        return "employee-home";
    }

    @GetMapping(path = "/all")
    public String getAllEmployees(Model model){
        var allEmployees = this.employeeService.getAll();
        model.addAttribute("employees", allEmployees);
        return "index";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") long id, Model model){
        Employee employeeById = this.employeeService.findById(id);

        model.addAttribute(
                "fullname", employeeById.getFirstName() + " " + employeeById.getLastName());
        model.addAttribute("id", id);
        this.employeeService.deleteEmployee(id);
        return "redirect:/employee/all";
    }

    @GetMapping(path = "/{id}")
    public String getEmployeeById(@PathVariable("id") long id, Model model){
        Employee employee = this.employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "single-employee";
    }


    @GetMapping(path = "/save")
    public String saveEmployee(Model model){
        model.addAttribute("user", new Employee());
        return "save-employee";
    }

    @PostMapping(path = "/save")
    public String saveEmployeeOrUpdate(@Valid @ModelAttribute Employee employee){
        this.employeeService.saveEmployee(employee);
        return "redirect:/employee/all";
    }


    @GetMapping(path = "/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){

        Employee employee = this.employeeService.findById(id);

        model.addAttribute("user", employee);
        return "update-employee";
    }

    @PostMapping(path = "/update")
    public String updateUser(@ModelAttribute Employee employee){
        this.employeeService.updateEmployee(employee.getId(), employee);
        return "redirect:/employee/all";
    }

}
