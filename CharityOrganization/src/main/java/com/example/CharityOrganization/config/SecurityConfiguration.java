package com.example.CharityOrganization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.example.CharityOrganization.service.UserLoginGuard.UsersDetailsService;
import com.example.CharityOrganization.utils.Guard.AuthenticationSuccessHandler;
import com.example.CharityOrganization.utils.Guard.CustomLogoutSuccessHandler;
import com.example.CharityOrganization.utils.JWT_tokens.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
public class SecurityConfiguration {

    @Autowired
    private UsersDetailsService UserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterAfter(new JwtAuthenticationFilter(), BasicAuthenticationFilter.class)

            .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/Sponsor/register","/logout","/regist/Register.html","/regist/SponsorRegister.html","/regist/VolunteerRegister.html","Volunteer/register","/Donor/register","tokens/find","/regist/DonorRegister.html").permitAll();

                    registry.requestMatchers("/Homepage/**").hasAnyRole("SPONSOR","DONOR","ADMIN","MASTER_ADMIN","VOLUNTEER");

                    registry.requestMatchers("/Master/Admin/**","/Admin/Admin_managment.html").hasRole("MASTER_ADMIN");
                    registry.requestMatchers("/Admin/**").hasAnyRole("ADMIN","MASTER_ADMIN");





                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                        .loginPage("/login.html")
                        .permitAll()
                        .successHandler(new AuthenticationSuccessHandler());


                })
                .sessionManagement( session->session
                        .sessionFixation()
                        .migrateSession()
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/logout")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/logout")
                )
                .logout(logout->logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler(UserDetailsService))

                )
                .build();
    }
    

    @SuppressWarnings("rawtypes")
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }
    

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(UserDetailsService);
    }


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(UserDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}