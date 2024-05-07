package com.example.CharityOrganization.model.Campaign;

public class CampaignDTO {
    private String decryptedName;
    private String decryptedGoal;
    private double decryptedTotalDonations;

    public String getDecryptedName() {
        return decryptedName;
    }

    public void setDecryptedName(String decryptedName) {
        this.decryptedName = decryptedName;
    }

    public String getDecryptedGoal() {
        return decryptedGoal;
    }

    public void setDecryptedGoal(String decryptedGoal) {
        this.decryptedGoal = decryptedGoal;
    }

    public double getDecryptedTotalDonations() {
        return decryptedTotalDonations;
    }

    public void setDecryptedTotalDonations(double decryptedTotalDonations) {
        this.decryptedTotalDonations = decryptedTotalDonations;
    }
}
