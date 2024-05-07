package com.example.CharityOrganization.model.Admin;

public class AdminDTO {
    private int admin_id;
    private String DecryptedPhoneNumber;
    private String DecryptrdAddress;
    private String DecryptedEmail;
    private String DecryptedName;


    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getDecryptedPhoneNumber() {
        return DecryptedPhoneNumber;
    }

    public void setDecryptedPhoneNumber(String DecryptedPhoneNumber) {
        this.DecryptedPhoneNumber = DecryptedPhoneNumber;
    }


    public String getDecryptedName() {
        return DecryptedName;
    }

    public void setDecryptedName(String name) {
        this.DecryptedName = name;
    }

    public String getDecryptrdAddress() {
        return DecryptrdAddress;
    }

    public void setDecryptrdAddress(String DecryptrdAddress) {
        this.DecryptrdAddress = DecryptrdAddress;
    }

    public String getDecryptedEmail() {
        return DecryptedEmail;
    }

    public void setDecryptedEmail(String DecryptedEmail) {
        this.DecryptedEmail = DecryptedEmail;
    }

}
