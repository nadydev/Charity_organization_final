package com.example.CharityOrganization.model.many_to_many_models;
import com.example.CharityOrganization.model.Campaign.Campaign;
import com.example.CharityOrganization.model.Donor.Donor;

import jakarta.persistence.*;

@Entity
@Table(name = "donor_campaign" ,uniqueConstraints = @UniqueConstraint(columnNames = {"donor_id", "campaign_id"}))
public class DonorCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private Donor donor;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column(name = "donor_donation",columnDefinition = "double default 0")
    private double donorDonation;

    // Constructors, getters, and setters
    public DonorCampaign() {
    }

    public DonorCampaign(Donor donor, Campaign campaign, double donorDonation) {
        this.donor = donor;
        this.campaign = campaign;
        this.donorDonation = donorDonation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public double getDonorDonation() {
        return donorDonation;
    }

    public void setDonorDonation(double donorDonation) {
        this.donorDonation = donorDonation;
    }
}
