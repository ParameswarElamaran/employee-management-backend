package com.parameswar.backend.controller;

import com.parameswar.backend.entity.Employee;
import com.parameswar.backend.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@CrossOrigin(
    origins = {
        "http://localhost:3000",
        "https://employee-management-frontend-rho-flame.vercel.app"
    },
    methods = {
        org.springframework.web.bind.annotation.RequestMethod.GET,
        org.springframework.web.bind.annotation.RequestMethod.POST,
        org.springframework.web.bind.annotation.RequestMethod.PUT,
        org.springframework.web.bind.annotation.RequestMethod.DELETE
    }
)
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/api/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/api/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    @DeleteMapping("/api/employees/{id}")
public void deleteEmployee(@PathVariable Long id) {
    employeeRepository.deleteById(id);
}
@PutMapping("/api/employees/{id}")
public Employee updateEmployee(
        @PathVariable Long id,
        @RequestBody Employee updatedEmployee) {

    Employee employee = employeeRepository.findById(id)
            .orElseThrow();

    employee.setName(updatedEmployee.getName());
    employee.setRole(updatedEmployee.getRole());

    return employeeRepository.save(employee);
}
}