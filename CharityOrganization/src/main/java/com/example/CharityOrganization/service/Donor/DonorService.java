package com.example.CharityOrganization.service.Donor;
import com.example.CharityOrganization.repository.DonorRepository;
import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.Donor.DonorDTO;








@Service
public class DonorService  {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ValidationUtils validation;

    @Autowired 
    private PasswordEncoder  passwordEncoder;



    public List<DonorDTO> getDonorsWithDecryptedData() {
        List<Donor> Donors = donorRepository.findAll();
        List<DonorDTO> decryptedDonors = new ArrayList<>();
        Donors.forEach(donor -> {
            try {
                String decryptedDate = SingleDESEncryption.decrypt(donor.getBirthDate());
                String decryptedNumber = SingleDESEncryption.decrypt(donor.getPhoneNumber());
                Integer donor_id = donor.getId();

                String decryptedEmail = SingleDESEncryption.decrypt(donor.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(donor.getUser().getUsername());
              //  String decryptedRole =  SingleDESEncryption.decrypt(donor.getUser().getRole());
                DonorDTO donorDTO = new DonorDTO();
                
                donorDTO.setDecryptedBirthDate(decryptedDate);
                donorDTO.setDecryptedPhoneNumber(decryptedNumber);
                donorDTO.setDecryptedUsername(decryptedUsername);
                donorDTO.setDecryptedEmail(decryptedEmail);
                donorDTO.setDonor_id(donor_id);
              //  donorDTO.setDecryptedRole(decryptedRole);
                decryptedDonors.add(donorDTO);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
        });
        return decryptedDonors;
    }



    @Transactional
    public void addDonorWithUser(Donor donor) {
        if (!isValidDonor(donor)) {
            throw new IllegalArgumentException("Invalid sponsor data.");
        }
    
        Users user = donor.getUser();
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
    
        Users newUser = new Users();
        try {
            newUser.setUsername(SingleDESEncryption.encrypt(user.getUsername()));
            newUser.setEmail(SingleDESEncryption.encrypt(user.getEmail()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("DONOR");

       Donor newDonor = new Donor();
        try {
            newDonor.setBirthDate(SingleDESEncryption.encrypt(donor.getBirthDate()));
            newDonor.setPhoneNumber(SingleDESEncryption.encrypt(donor.getPhoneNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    
        newUser.setDonor(newDonor);
        userRepository.save(newUser);
        newDonor.setUser(newUser);
        donorRepository.save(newDonor);
    }
    








    public DonorDTO findDonorWithUserAndDecrypt(Integer id) {
        Donor donor = donorRepository.findDonorWithUserById(id);
        if (donor != null) {
            DonorDTO donorDTO = new DonorDTO();
            try {
                String decryptedDate = SingleDESEncryption.decrypt(donor.getBirthDate());
                String decryptedNumber = SingleDESEncryption.decrypt(donor.getPhoneNumber());
                String decryptedEmail = SingleDESEncryption.decrypt(donor.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(donor.getUser().getUsername());
          //      String decryptedRole =  SingleDESEncryption.decrypt(donor.getUser().getRole());

                donorDTO.setDonor_id(id);
                donorDTO.setDecryptedBirthDate(decryptedDate);
                donorDTO.setDecryptedPhoneNumber(decryptedNumber);
                donorDTO.setDecryptedEmail(decryptedEmail);
                donorDTO.setDecryptedUsername(decryptedUsername);
                donorDTO.setDecryptedRole(donor.getUser().getRole());
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
            return donorDTO;
        } else {
            return null; // Sponsor not found with the given ID
        }
    }

// new 

public DonorDTO findDonorWithUser_IdAndDecrypt(Integer id) {
    Donor donor = donorRepository.findByUserId(id);
    if (donor != null) {
        DonorDTO donorDTO = new DonorDTO();
        try {
            String decryptedDate = SingleDESEncryption.decrypt(donor.getBirthDate());
            String decryptedNumber = SingleDESEncryption.decrypt(donor.getPhoneNumber());
            String decryptedEmail = SingleDESEncryption.decrypt(donor.getUser().getEmail());
            String decryptedUsername = SingleDESEncryption.decrypt(donor.getUser().getUsername());
      //      String decryptedRole =  SingleDESEncryption.decrypt(donor.getUser().getRole());

            donorDTO.setDonor_id(id);
            donorDTO.setDecryptedBirthDate(decryptedDate);
            donorDTO.setDecryptedPhoneNumber(decryptedNumber);
            donorDTO.setDecryptedEmail(decryptedEmail);
            donorDTO.setDecryptedUsername(decryptedUsername);
            donorDTO.setDecryptedRole(donor.getUser().getRole());
        } catch (Exception e) {
            e.printStackTrace(); // Handle decryption error
        }
        return donorDTO;
    } else {
        return null; // Sponsor not found with the given ID
    }
}




    @Transactional
    public String deleteDonor(Integer id) {
        Optional<Donor> optionalDonor = donorRepository.findById(id);
        if (optionalDonor.isPresent()) {
            Donor donor = optionalDonor.get();
            Users user = donor.getUser(); 
            if (user != null) {
                userRepository.delete(user); 
            }
            donorRepository.delete(donor); 
            return "Deleted Successfully";
        } else {
            return "No Id found to be deleted";
        }
    }





    @Transactional
    public String updateDonor(Integer id, Donor updatedDonor) {
        Optional<Donor> optionalSponsor = donorRepository.findById(id);
        if (optionalSponsor.isPresent()) {
            Donor donor = optionalSponsor.get();
            
            try {
                String encryptedDate = SingleDESEncryption.encrypt(updatedDonor.getBirthDate());
                String encryptedNumber = SingleDESEncryption.encrypt(updatedDonor.getPhoneNumber());
                donor.setBirthDate(encryptedDate);
                donor.setPhoneNumber(encryptedNumber);
            } catch (Exception e) {
                e.printStackTrace(); // Handle encryption error
                return "Failed to update donor. Encryption error occurred.";
            }
            
            Users user = donor.getUser();
            if (user != null) {
                try {
                    String encryptedUsername = SingleDESEncryption.encrypt(updatedDonor.getUser().getUsername());
                    String encryptedEmail = SingleDESEncryption.encrypt(updatedDonor.getUser().getEmail());
                    user.setUsername(encryptedUsername);
                    user.setEmail(encryptedEmail);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle encryption error
                    return "Failed to update user associated with donor. Encryption error occurred.";
                }
                userRepository.save(user); // Save the updated user
            }
            
            donorRepository.save(donor); // Save the updated donor with encrypted data
            return "Updated Successfully";
        } else {
            return "Donor not found with id: " + id;
        }
    }



    private boolean isValidDonor(Donor donor) {
        return donor != null &&
                validation.isValidString(donor.getUser().getUsername()) &&
                validation.isValidEmail(donor.getUser().getEmail()) &&
                validation.isValidPhoneNumber(donor.getPhoneNumber());
    }



    // public Long getTotalDonation(Integer donorId) {
        
    //     //return donorRepository.find
       
    // }
}