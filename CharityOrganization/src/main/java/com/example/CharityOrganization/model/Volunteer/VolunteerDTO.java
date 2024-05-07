package com.example.CharityOrganization.model.Volunteer;

public class VolunteerDTO {
    private String decryptedEmail; 
    private String decryptedUsername; 
    private String decryptedAddress;
    private Integer volunteer_id ;

    
public String getDecryptedEmail() {
    return decryptedEmail;
}

// Setter for decryptedEmail
public void setDecryptedEmail(String decryptedEmail) {
    this.decryptedEmail = decryptedEmail;
}

// Getter for decryptedUsername
public String getDecryptedUsername() {
    return decryptedUsername;
}

// Setter for decryptedUsername
public void setDecryptedUsername(String decryptedUsername) {
    this.decryptedUsername = decryptedUsername;
}

// Getter for decryptedAddress
public String getDecryptedAddress() {
    return decryptedAddress;
}

// Setter for decryptedAddress
public void setDecryptedAddress(String decryptedAddress) {
    this.decryptedAddress = decryptedAddress;
}

// Getter for volunteer_id
public Integer getVolunteer_id() {
    return volunteer_id;
}

// Setter for volunteer_id
public void setVolunteer_id(Integer volunteer_id) {
    this.volunteer_id = volunteer_id;
}



}
