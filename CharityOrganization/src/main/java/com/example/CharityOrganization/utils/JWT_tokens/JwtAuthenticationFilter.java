package com.example.CharityOrganization.utils.JWT_tokens;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {

                String header = request.getHeader(HttpHeaders.AUTHORIZATION);

                if (header == null || !header.startsWith("Bearer ")) {
                    filterChain.doFilter(request, response);
                    return;
                }
            
                try {
                    String token = header.substring(7);
                    Claims claims = jwtTokenUtil.extractAllClaims(token);
                    String username = claims.getSubject();
                    String role = (String) claims.get("role");
            
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(username, null, null);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
            
                    if (role.equals("ADMIN")) {
                        // Allow access to admin endpoints
                        filterChain.doFilter(new HttpServletRequestWrapper(request), response);
                    } else if (role.equals("SPONSOR")) {
                        // Allow access to user endpoints
                        filterChain.doFilter(new HttpServletRequestWrapper(request), response);
                    } else {
                        response.sendError(HttpStatus.FORBIDDEN.value(), "Unauthorized access");
                    }
                } catch (Exception e) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired token");
                }
            }
    
    }

