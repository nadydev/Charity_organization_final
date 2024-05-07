package com.example.CharityOrganization.utils.Guard;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.CharityOrganization.service.UserLoginGuard.UsersDetailsService;


public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final UsersDetailsService userDetailsService;

    public CustomLogoutSuccessHandler(UsersDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }



    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {


                if (authentication != null) {
                    String username = authentication.getName();
                    userDetailsService.deleteSessionAndTokens(username);
                }
                response.sendRedirect("/login.html");        
                
    }
}
