package com.example.CharityOrganization.service.sponsor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.Sponsor.Sponsor;
import com.example.CharityOrganization.model.Sponsor.SponsorDTO;
import com.example.CharityOrganization.repository.SponsorRepository;
import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtils validation;

    @Autowired
    private PasswordEncoder passwordEncoder;












    public List<SponsorDTO> getSponsorsWithDecryptedData() {
        List<Sponsor> sponsors = sponsorRepository.findAll();
        List<SponsorDTO> decryptedSponsors = new ArrayList<>();
        sponsors.forEach(sponsor -> {
            try {
                String decryptedCompanyLocation = SingleDESEncryption.decrypt(sponsor.getCompanyLocation());
                String decryptedBusinessNumber = SingleDESEncryption.decrypt(sponsor.getBusinessNumber());
                Integer sponsor_id = sponsor.getId();

                // Assuming Sponsor has an associated User
                String decryptedEmail = SingleDESEncryption.decrypt(sponsor.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(sponsor.getUser().getUsername());

                SponsorDTO sponsorDTO = new SponsorDTO();
                sponsorDTO.setDecryptedCompanyLocation(decryptedCompanyLocation);
                sponsorDTO.setDecryptedBusinessNumber(decryptedBusinessNumber);
                sponsorDTO.setDecryptedEmail(decryptedEmail);
                sponsorDTO.setDecryptedUsername(decryptedUsername);
                sponsorDTO.setSponsorId(sponsor_id);
                decryptedSponsors.add(sponsorDTO);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
        });
        return decryptedSponsors;
    }



    @Transactional
    public void addSponsorWithUser(Sponsor sponsor) {
        if (!isValidSponsor(sponsor)) {
            throw new IllegalArgumentException("Invalid sponsor data.");
        }
    
        Users user = sponsor.getUser();
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
        newUser.setRole("SPONSOR");

        Sponsor newSponsor = new Sponsor();
        try {
            newSponsor.setCompanyLocation(SingleDESEncryption.encrypt(sponsor.getCompanyLocation()));
            newSponsor.setBusinessNumber(SingleDESEncryption.encrypt(sponsor.getBusinessNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    
        newUser.setSponsor(newSponsor);
        userRepository.save(newUser);
        newSponsor.setUser(newUser);
        sponsorRepository.save(newSponsor);
    }
    








    public SponsorDTO findSponsorWithUserAndDecrypt(Integer id) {
        Sponsor sponsor = sponsorRepository.findSponsorWithUserById(id);
        if (sponsor != null) {
            SponsorDTO sponsorDTO = new SponsorDTO();
            try {
                String decryptedCompanyLocation = SingleDESEncryption.decrypt(sponsor.getCompanyLocation());
                String decryptedBusinessNumber = SingleDESEncryption.decrypt(sponsor.getBusinessNumber());
                String decryptedEmail = SingleDESEncryption.decrypt(sponsor.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(sponsor.getUser().getUsername());

                sponsorDTO.setSponsorId(sponsor.getId()); // Set sponsor ID
                sponsorDTO.setDecryptedCompanyLocation(decryptedCompanyLocation);
                sponsorDTO.setDecryptedBusinessNumber(decryptedBusinessNumber);
                sponsorDTO.setDecryptedEmail(decryptedEmail);
                sponsorDTO.setDecryptedUsername(decryptedUsername);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
            return sponsorDTO;
        } else {
            return null; // Sponsor not found with the given ID
        }
    }





    @Transactional
    public String deleteSponsor(Integer id) {
        Optional<Sponsor> optionalSponsor = sponsorRepository.findById(id);
        if (optionalSponsor.isPresent()) {
            Sponsor sponsor = optionalSponsor.get();
            Users user = sponsor.getUser(); 
            if (user != null) {
                userRepository.delete(user); 
            }
            sponsorRepository.delete(sponsor); 
            return "Deleted Successfully";
        } else {
            return "No Id found to be deleted";
        }
    }





    @Transactional
    public String updateSponsor(Integer id, Sponsor updatedSponsor) {
        Optional<Sponsor> optionalSponsor = sponsorRepository.findById(id);
        if (optionalSponsor.isPresent()) {
            Sponsor sponsor = optionalSponsor.get();
            
            try {
                String encryptedCompanyLocation = SingleDESEncryption.encrypt(updatedSponsor.getCompanyLocation());
                String encryptedBusinessNumber = SingleDESEncryption.encrypt(updatedSponsor.getBusinessNumber());
                sponsor.setCompanyLocation(encryptedCompanyLocation);
                sponsor.setBusinessNumber(encryptedBusinessNumber);
            } catch (Exception e) {
                e.printStackTrace(); // Handle encryption error
                return "Failed to update sponsor. Encryption error occurred.";
            }
            
            Users user = sponsor.getUser();
            if (user != null) {
                try {
                    String encryptedUsername = SingleDESEncryption.encrypt(updatedSponsor.getUser().getUsername());
                    String encryptedEmail = SingleDESEncryption.encrypt(updatedSponsor.getUser().getEmail());
                    user.setUsername(encryptedUsername);
                    user.setEmail(encryptedEmail);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle encryption error
                    return "Failed to update user associated with sponsor. Encryption error occurred.";
                }
                userRepository.save(user); // Save the updated user
            }
            
            sponsorRepository.save(sponsor); // Save the updated sponsor with encrypted data
            return "Updated Successfully";
        } else {
            return "Sponsor not found with id: " + id;
        }
    }



    private boolean isValidSponsor(Sponsor sponsor) {
        return sponsor != null &&
                validation.isValidString(sponsor.getUser().getUsername()) &&
                validation.isValidEmail(sponsor.getUser().getEmail()) &&
                validation.isValidPhoneNumber(sponsor.getBusinessNumber());
    }

    }