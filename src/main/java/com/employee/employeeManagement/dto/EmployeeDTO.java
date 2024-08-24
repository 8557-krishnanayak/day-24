package com.employee.employeeManagement.dto;

import com.employee.employeeManagement.Modal.Address;
import com.employee.employeeManagement.Modal.Department;
import com.employee.employeeManagement.Modal.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate date;
    private String phoneNumber;
    private DepartmentDTO department;
    private List<AddressDTO> addresses;
    private List<Project> projects;
}
