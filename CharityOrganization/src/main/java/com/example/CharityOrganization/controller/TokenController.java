package com.example.CharityOrganization.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.CharityOrganization.model.JwtToken;
import com.example.CharityOrganization.service.UserLoginGuard.UsersDetailsService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final UsersDetailsService usersDetailsService;

    @Autowired
    public TokenController(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @GetMapping("/find")
    public JwtToken findTokenByUsernameAndType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the current username
        String type = "ACCESS"; // Or any other type you want to search for
        return usersDetailsService.findTokenByUsernameAndType(username, type);
    }

    @GetMapping("/getUser/{token}")
    public Integer findUserInfoByToken(@PathVariable("token") String token) {



        String type = "ACCESS"; // Or any other type you want to search for
        return usersDetailsService.findUserIDByTokenAndType(token, type);

    }
}

