package com.example.CharityOrganization.service.Volunteer;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.model.Volunteer.VolunteerDTO;
import com.example.CharityOrganization.repository.VolunteerRepository;
import com.example.CharityOrganization.repository.UserRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtils validation;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<VolunteerDTO> getVolunteersWithDecryptedData() {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        List<VolunteerDTO> decryptedVolunteers = new ArrayList<>();
        volunteers.forEach(volunteer -> {
            try {
                String decryptedEmail = SingleDESEncryption.decrypt(volunteer.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(volunteer.getUser().getUsername());
                String decryptedAddress = SingleDESEncryption.decrypt(volunteer.getAddress());
                Integer volunteer_id = volunteer.getId();

                VolunteerDTO volunteerDTO = new VolunteerDTO();
                volunteerDTO.setDecryptedEmail(decryptedEmail);
                volunteerDTO.setDecryptedUsername(decryptedUsername);
                volunteerDTO.setDecryptedAddress(decryptedAddress);
                volunteerDTO.setVolunteer_id(volunteer_id);
                decryptedVolunteers.add(volunteerDTO);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
        });
        return decryptedVolunteers;
    }

    @Transactional
    public void addVolunteerWithUser(Volunteer volunteer) {
        if (!isValidVolunteer(volunteer)) {
            throw new IllegalArgumentException("Invalid volunteer data.");
        }

        Users user = volunteer.getUser();
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
        newUser.setRole("VOLUNTEER");

        Volunteer newVolunteer = new Volunteer();
        try {
            newVolunteer.setAddress(SingleDESEncryption.encrypt(volunteer.getAddress()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        newUser.setVolunteer(newVolunteer);
        userRepository.save(newUser);
        newVolunteer.setUser(newUser);
        volunteerRepository.save(newVolunteer);
    }

    public VolunteerDTO findVolunteerWithUserAndDecrypt(Integer id) {
        Volunteer volunteer = volunteerRepository.findVolunteerWithUserById(id);
        if (volunteer != null) {
            VolunteerDTO volunteerDTO = new VolunteerDTO();
            try {
                String decryptedEmail = SingleDESEncryption.decrypt(volunteer.getUser().getEmail());
                String decryptedUsername = SingleDESEncryption.decrypt(volunteer.getUser().getUsername());
                String decryptedAddress = SingleDESEncryption.decrypt(volunteer.getAddress());

                volunteerDTO.setVolunteer_id(id);
                volunteerDTO.setDecryptedEmail(decryptedEmail);
                volunteerDTO.setDecryptedUsername(decryptedUsername);
                volunteerDTO.setDecryptedAddress(decryptedAddress);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
            return volunteerDTO;
        } else {
            return null; // Volunteer not found with the given ID
        }
    }

    @Transactional
    public String deleteVolunteer(Integer id) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isPresent()) {
            Volunteer volunteer = optionalVolunteer.get();
            Users user = volunteer.getUser();
            if (user != null) {
                userRepository.delete(user);
            }
            volunteerRepository.delete(volunteer);
            return "Deleted Successfully";
        } else {
            return "No Id found to be deleted";
        }
    }

    @Transactional
    public String updateVolunteer(Integer id, Volunteer updatedVolunteer) {
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        if (optionalVolunteer.isPresent()) {
            Volunteer volunteer = optionalVolunteer.get();

            try {
                String encryptedAddress = SingleDESEncryption.encrypt(updatedVolunteer.getAddress());
                volunteer.setAddress(encryptedAddress);
            } catch (Exception e) {
                e.printStackTrace(); // Handle encryption error
                return "Failed to update volunteer. Encryption error occurred.";
            }

            Users user = volunteer.getUser();
            if (user != null) {
                try {
                    String encryptedUsername = SingleDESEncryption.encrypt(updatedVolunteer.getUser().getUsername());
                    String encryptedEmail = SingleDESEncryption.encrypt(updatedVolunteer.getUser().getEmail());
                    user.setUsername(encryptedUsername);
                    user.setEmail(encryptedEmail);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle encryption error
                    return "Failed to update user associated with volunteer. Encryption error occurred.";
                }
                userRepository.save(user); // Save the updated user
            }

            volunteerRepository.save(volunteer); // Save the updated volunteer with encrypted data
            return "Updated Successfully";
        } else {
            return "Volunteer not found with id: " + id;
        }
    }

    private boolean isValidVolunteer(Volunteer volunteer) {
        return volunteer != null &&
                validation.isValidString(volunteer.getUser().getUsername()) &&
                validation.isValidEmail(volunteer.getUser().getEmail()) &&
                validation.isValidString(volunteer.getAddress());
    }
}
