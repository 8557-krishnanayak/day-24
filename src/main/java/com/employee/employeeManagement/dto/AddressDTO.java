package com.employee.employeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
    private Long AddressID;
    private String type;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
