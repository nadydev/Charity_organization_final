package com.example.CharityOrganization.service.JWT_Tokens;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.CharityOrganization.model.JwtToken;
import com.example.CharityOrganization.repository.JwtTokenRepository;

public class JWTService {
 @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public JwtToken findByUsernameAndType(String username, String type) {
        return jwtTokenRepository.findByUsernameAndType(username, type);
    }   
}
