package com.employee.employeeManagement.controller;

import com.employee.employeeManagement.Modal.Employee;
import com.employee.employeeManagement.service.EmployeeService;
import com.employee.employeeManagement.util.TokenUtility;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access")
public class AccessController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/login")
    public ResponseEntity<?> tokenStore(
            @RequestParam("emp_id") Long id,
            @RequestParam("firstname") String name,
            HttpServletResponse response
    ) {
        String token = employeeService.checkIdAndName(id, name);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("Auth is done");
    }

    @GetMapping("/token")
    public ResponseEntity<?> returnTokenStore(
            @RequestParam("emp_id") Long id,
            @RequestParam("firstname") String name
    ) {
        String token = employeeService.checkIdAndName(id, name);

        return ResponseEntity.ok(token);
    }


    @GetMapping("/doIGetAccess")
    public ResponseEntity<?> checkAccess(@CookieValue(value = "token", defaultValue = "Unknown") String token) {
        Employee employee = employeeService.checkValidToken(token);
        if (employee.getRole().equalsIgnoreCase("ADMIN"))
            return (ResponseEntity<?>) ResponseEntity.noContent();
        return ResponseEntity.ok("You have a access");
    }


    @GetMapping("/doIGetAccessUsingHeader")
    public ResponseEntity<?> checkAccessByHeader(@RequestHeader String token) {
        try {

            Employee employee = employeeService.checkValidToken(token);
            if (employee.getRole().equalsIgnoreCase("ADMIN")) {
                return ResponseEntity.ok("You have a access");

            }
            return new ResponseEntity<>("Yo have no Access", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.noContent();
        }
    }


}
