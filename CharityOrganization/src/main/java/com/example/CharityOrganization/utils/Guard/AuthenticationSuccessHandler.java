package com.example.CharityOrganization.utils.Guard;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("authenticatedUser", userDetails.getUsername());

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        




        if(isAdmin){
            setDefaultTargetUrl("/Admin/index.html");
            setAlwaysUseDefaultTargetUrl(true);
        } else {
            setDefaultTargetUrl("/Homepage/better_bridge/index.html");
            setAlwaysUseDefaultTargetUrl(true);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }


}
