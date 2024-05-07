package com.example.CharityOrganization.service.UpdateUser;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.UsersDTO;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.Donor.DonorDTO;
import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;

import jakarta.transaction.Transactional;


@Service
public class UsersService {


    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Transactional
    public void updateUser(int userId, Users updatedUser) {
        if (!isValidUser(updatedUser)) {
            throw new IllegalArgumentException("Invalid User data.");
        }
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            try {
               
                String encryptedUsername = SingleDESEncryption.encrypt(updatedUser.getUsername());
                String encryptedEmail = SingleDESEncryption.encrypt(updatedUser.getEmail());
                String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
                
                
                user.setUsername(encryptedUsername) ;
                user.setEmail(encryptedEmail);
                user.setPassword(hashedPassword);    
                //He cant change his role 
    
                userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace(); // Handle encryption error
            }
        } else {
            throw new IllegalArgumentException("Event not found with ID: " + userId);
        }
    }


        public UsersDTO findUserAndDecrypt(Integer id) throws Exception {
            Optional<Users> userOptional = userRepository.findById(id);
            UsersDTO usersDTO = new UsersDTO();
            
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                String decryptedEmail = SingleDESEncryption.decrypt(user.getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(user.getUsername());
              //  String decryptedRole =  SingleDESEncryption.decrypt(user.getRole());
                
                usersDTO.setId(id);
                usersDTO.setEmail(decryptedEmail);
                usersDTO.setUsername(decryptedUsername);
                usersDTO.setRole(user.getRole());

                return usersDTO;
            }else{
                
                return null; 
            }
        }
    
    
    


    private boolean isValidUser(Users user) {
        return user != null &&
                user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getEmail() != null && !user.getEmail().isEmpty() && 
                user.getPassword() != null && !user.getPassword().isEmpty() ;

    }
}

    
    












  




    
