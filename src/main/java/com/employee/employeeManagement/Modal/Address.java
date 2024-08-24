package com.employee.employeeManagement.Modal;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long AddressID;

    private String type;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}

