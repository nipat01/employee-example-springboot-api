package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public  Employee addEmployee(Employee employee) {
        System.out.println("employee");
        System.out.println(employee);
        employee.setEmployeeCode((UUID.randomUUID().toString()));
        return  employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        System.out.println("[findAllEmployees]");
        return  employeeRepo.findAll();
    }

    public  Employee updateEmployee(Employee employee) {
        return  employeeRepo.save(employee);
    }

    public  Employee findEmployeeById(Long id) {
        return  employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id "+ id + " not found"));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
