package com.example.CharityOrganization.utils.Guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import com.example.CharityOrganization.service.UserLoginGuard.UsersDetailsService;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UsersDetailsService usersDetailsService;

    @Autowired
    public AuthenticationSuccessEventListener(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        usersDetailsService.generateAndSaveAccessToken(username);
        usersDetailsService.generateAndSaveRefreshToken(username);



        
    }
}
