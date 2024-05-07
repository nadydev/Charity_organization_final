package com.example.CharityOrganization.model.Donor;

public class DonorDTO {
    private String decryptedBirthDate;
    private String decryptedPhoneNumber;
    private String decryptedEmail;
    private String decryptedUsername;
    private Integer donor_id;
    private Long num_participants;
    private String decryptedRole ;


    public String getDecryptedRole(){
        return decryptedRole;
    }

    public String getDecryptedBirthDate() {
        return decryptedBirthDate;
    }

    public void setDecryptedBirthDate(String decryptedBirthDate) {
        this.decryptedBirthDate = decryptedBirthDate;
    }

    public String getDecryptedPhoneNumber() {
        return decryptedPhoneNumber;
    }

    public void setDecryptedPhoneNumber(String decryptedPhoneNumber) {
        this.decryptedPhoneNumber = decryptedPhoneNumber;
    }

    public String getDecryptedEmail() {
        return decryptedEmail;
    }

    public void setDecryptedEmail(String decryptedEmail) {
        this.decryptedEmail = decryptedEmail;
    }

    public String getDecryptedUsername() {
        return decryptedUsername;
    }

    public void setDecryptedUsername(String decryptedUsername) {
        this.decryptedUsername = decryptedUsername;
    }

    public Integer getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(Integer donor_id) {
        this.donor_id = donor_id;
    }


    public Long getNumParticipants() {
        return num_participants;
    }

    public void setNumParticipants(Long  numParticipants) {
        this.num_participants= numParticipants;
    }

    public void setDecryptedRole(String decryptedRole){
        this.decryptedRole = decryptedRole;
    }
}
