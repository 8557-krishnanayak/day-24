package com.employee.employeeManagement.controller;

import com.employee.employeeManagement.Modal.Employee;
import com.employee.employeeManagement.dto.EmployeeDTO;
import com.employee.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<?>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmp(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.addEmployee(employeeDTO), HttpStatus.CREATED);
    }


}
