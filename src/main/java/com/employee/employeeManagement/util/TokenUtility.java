package com.employee.employeeManagement.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtility {
    private final String SERCRET = "qwertyuiop";

    public String createToken(Long id, String role) {
        return JWT.create()
                .withClaim("emp_id", id)
                .withClaim("role", role)
                .sign(Algorithm.HMAC256(SERCRET));
    }

    public String decodeForRole(String token) {
        String role = "";
        if (token != null) {
            role = JWT.require(Algorithm.HMAC256(SERCRET))
                    .build().verify(token).getClaim("role").asString();
        }
        return role;
    }

    public Long decodeForId(String token) {
        Long id = 0l;
        if (token != null) {
            id = JWT.require(Algorithm.HMAC256(SERCRET))
                    .build().verify(token).getClaim("emp_id").asLong();
        }
        return id;
    }
}
