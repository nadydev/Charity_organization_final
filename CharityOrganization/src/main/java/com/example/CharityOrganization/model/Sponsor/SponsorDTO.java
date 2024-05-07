package com.example.CharityOrganization.model.Sponsor;

public class SponsorDTO {
    private String decryptedCompanyLocation;
    private String decryptedBusinessNumber;
    private String decryptedEmail; 
    private String decryptedUsername;
    private Integer sponsor_id ;

    public SponsorDTO() {}

    public SponsorDTO(Integer sponsor_id , String decryptedCompanyLocation, String decryptedBusinessNumber, String decryptedEmail, String decryptedUsername) {
        this.sponsor_id = sponsor_id;
        this.decryptedCompanyLocation = decryptedCompanyLocation;
        this.decryptedBusinessNumber = decryptedBusinessNumber;
        this.decryptedEmail = decryptedEmail;
        this.decryptedUsername = decryptedUsername;
    }

    public String getDecryptedCompanyLocation() {
        return decryptedCompanyLocation;
    }

    public void setDecryptedCompanyLocation(String decryptedCompanyLocation) {
        this.decryptedCompanyLocation = decryptedCompanyLocation;
    }

    public String getDecryptedBusinessNumber() {
        return decryptedBusinessNumber;
    }

    public void setDecryptedBusinessNumber(String decryptedBusinessNumber) {
        this.decryptedBusinessNumber = decryptedBusinessNumber;
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


    public Integer getSponsorId() {
        return sponsor_id ;
    }

    public void setSponsorId(Integer id) {
        this.sponsor_id = id;
    }



}
