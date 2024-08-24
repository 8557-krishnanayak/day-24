package com.employee.employeeManagement.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDate date;
    private String phoneNumber;

    private String role;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Department.class)
    @JoinColumn(name = "emp_dept")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "emp_address")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Project.class)
    @JoinColumn(name = "emp_project")
    private List<Project> projects;


    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = "employee";
        }
    }

}
