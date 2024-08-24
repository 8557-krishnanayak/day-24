package com.employee.employeeManagement.service;

import com.employee.employeeManagement.Modal.Employee;
import com.employee.employeeManagement.dto.EmployeeDTO;
import com.employee.employeeManagement.repository.EmployeeRepository;
import com.employee.employeeManagement.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    private ModelMapper modelMapper;

    public List<Employee> getAllEmp() {
        return employeeRepository.findAll();
    }


    private EmployeeDTO convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee save_emp = employeeRepository.save(convertToEntity(employeeDTO));
        return convertToDto(save_emp);
    }

    public Employee getEmpById(Long id) {
        return employeeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public String checkIdAndName(Long id, String name) {
        Employee employee = getEmpById(id);
        if (employee.getFirstname().equals(name)) {
            return tokenUtility.createToken(id, employee.getRole());
        }
        return "";
    }

    public Employee checkValidToken(String token) {
        Long id = tokenUtility.decodeForId(token);
        Employee empById = getEmpById(id);
        return empById;
    }
}
