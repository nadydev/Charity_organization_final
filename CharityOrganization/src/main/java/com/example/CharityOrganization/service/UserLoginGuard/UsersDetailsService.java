package com.example.CharityOrganization.service.UserLoginGuard;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.JWT_tokens.JwtTokenUtil;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;

import jakarta.transaction.Transactional;

import com.example.CharityOrganization.model.JwtToken;
import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.repository.JwtTokenRepository;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtTokenRepository jwtTokenRepository;

    @Autowired
    public UsersDetailsService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, JwtTokenRepository jwtTokenRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            String encryptedName = SingleDESEncryption.encrypt(name);
            Optional<Users> user = userRepository.findByUsername(encryptedName);
            if (user.isPresent()) {
                var userObj = user.get();
                String decryptedPassword = userObj.getPassword();
                return User.builder()
                        .username(userObj.getUsername())
                        .password(decryptedPassword)
                        .roles(getRoles(userObj))
                        .build();
            } else {
                throw new UsernameNotFoundException(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while loading user by username", e);

        }

 
    }

    private String[] getRoles(Users user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        } else {
            return user.getRole().split(",");
        }
    }

    public String generateAndSaveAccessToken(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Users userObj = user.get();
            String token = jwtTokenUtil.generateAccessToken(username, userObj.getRole());
    
            JwtToken jwtToken = new JwtToken();
            jwtToken.setUsername(username);
            jwtToken.setToken(token);
            jwtToken.setType("ACCESS");
            jwtToken.setUserRole(userObj.getRole()); // Set the user role
            
//          
            jwtToken.setUser_id(userObj.getId());

            jwtTokenRepository.save(jwtToken);
    
            return token;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
    
    public String generateAndSaveRefreshToken(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Users userObj = user.get();
            String refreshToken = jwtTokenUtil.generateRefreshToken(username, userObj.getRole());
    
            JwtToken jwtToken = new JwtToken();
            jwtToken.setUsername(username);
            jwtToken.setToken(refreshToken);
            jwtToken.setType("REFRESH");
            jwtToken.setUserRole(userObj.getRole()); // Set the user role
            jwtTokenRepository.save(jwtToken);
    
            return refreshToken;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }


    @Transactional
    public void deleteSessionAndTokens(String username) {
        jwtTokenRepository.deleteByUsername(username);
    }


    public JwtToken findTokenByUsernameAndType(String username, String type) {
        return jwtTokenRepository.findByUsernameAndType(username, type);
    }


   /*  public JwtToken findIdByTokenAndType(String token , String type){
        return jwtTokenRepository.findIdByTokenAndType(token, type);
    }*/



    public Integer findUserIDByTokenAndType(String token, String type ) {
        return jwtTokenRepository.findIdByTokenAndType(token, type);
    }



}
