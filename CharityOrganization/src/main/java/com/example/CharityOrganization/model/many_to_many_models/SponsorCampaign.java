package com.example.CharityOrganization.model.many_to_many_models;

import com.example.CharityOrganization.model.Campaign.Campaign;
import com.example.CharityOrganization.model.Sponsor.Sponsor;

import jakarta.persistence.*;

@Entity
@Table(name = "sponsor_campaign")
public class SponsorCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sponsor_id", referencedColumnName = "ID")
    private Sponsor sponsor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    private Campaign campaign;


    @Column(name="sponsor_donations",columnDefinition = "double default 0")
    private double sponsorDonation;






    public SponsorCampaign() {
    }

    public SponsorCampaign(Sponsor sponsor, Campaign campaign) {
        this.sponsor = sponsor;
        this.campaign = campaign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
