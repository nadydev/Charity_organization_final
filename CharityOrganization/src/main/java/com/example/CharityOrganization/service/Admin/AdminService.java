package com.example.CharityOrganization.service.Admin;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.Admin.Admin;
import com.example.CharityOrganization.model.Admin.AdminDTO;
import com.example.CharityOrganization.repository.AdminRepository;
import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;
import com.example.CharityOrganization.utils.encryption.TripleDESEncryption;
import com.example.CharityOrganization.utils.hashing.Hasher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtils validation;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private Hasher hasher;



    
    @Transactional
    public void addAdminWithUser(Admin admin) {
        if (!isValidAdmin(admin)) {
            throw new IllegalArgumentException("Invalid admin data.");
        }
    
        Users user = admin.getUser();
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
    
        // Hash the email before saving
        String hashedEmail = hasher.hashAttribute(user.getEmail());
        System.out.println(hashedEmail);
    
        Users newUser = new Users();
        try {
            // Encrypt sensitive fields
            newUser.setUsername(SingleDESEncryption.encrypt(user.getUsername()));
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEmail(hashedEmail);
            newUser.setRole("ADMIN");
            newUser.setAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle encryption errors appropriately
            throw new RuntimeException("Error encrypting user data.", e);
        }
    
        userRepository.save(newUser);
    
        admin.setUser(newUser);
    
        try {
            // Encrypt admin's sensitive fields
            admin.setPhoneNumber(TripleDESEncryption.encrypt(admin.getPhoneNumber()));
            admin.setAddress(TripleDESEncryption.encrypt(admin.getAddress()));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle encryption errors appropriately
            throw new RuntimeException("Error encrypting admin data.", e);
        }
    
        adminRepository.save(admin);
    }
    

    public List<AdminDTO> getAdminsWithDecryptedData() {
        List<Admin> admins = adminRepository.findAllAdminsWithUsers();
        List<AdminDTO> decryptedAdmins = new ArrayList<>();
        admins.forEach(admin -> {
            try {
                String decryptedPhoneNumber = TripleDESEncryption.decrypt(admin.getPhoneNumber());
                String decryptedAddress = TripleDESEncryption.decrypt(admin.getAddress());
                String decryptedUsername = SingleDESEncryption.decrypt(admin.getUser().getUsername());

                AdminDTO adminDTO = new AdminDTO();

                adminDTO.setAdmin_id(admin.getId());
                adminDTO.setDecryptedPhoneNumber(decryptedPhoneNumber);
                adminDTO.setDecryptrdAddress(decryptedAddress);
                adminDTO.setDecryptedEmail(admin.getUser().getEmail());
                adminDTO.setDecryptedName(decryptedUsername);

                decryptedAdmins.add(adminDTO);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
        });
        return decryptedAdmins;
    }




    @Transactional
    public String deleteAdminById(Integer id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            userRepository.deleteById(admin.getUser().getId());
            adminRepository.deleteById(id);
            return "Admin and associated user deleted successfully";
        } else {
            return "Admin not found with the given ID";
        }
    }


    private boolean isValidAdmin(Admin admin) {
        return admin!= null && validation.isValidEmail(admin.getUser().getEmail()) && validation.isValidPhoneNumber(admin.getPhoneNumber()) && validation.isValidString(admin.getUser().getUsername());
    }
}