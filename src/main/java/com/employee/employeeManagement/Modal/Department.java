package com.employee.employeeManagement.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue
    private Long deptId;

    @Column(name = "department_name")
    private String deptName;

    private String location;
}
