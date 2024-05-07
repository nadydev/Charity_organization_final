package com.example.CharityOrganization.model.Campaign;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.example.CharityOrganization.model.many_to_many_models.DonorCampaign;
import com.example.CharityOrganization.model.many_to_many_models.SponsorCampaign;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "goal", nullable = false)
    private String goal;

    @Column(name = "total_donations", columnDefinition = "double default 0")
    private double totalDonations;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DonorCampaign> donorCampaigns = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)    
    private Set<SponsorCampaign> sponsorCampaigns = new HashSet<>();
    


    
    public Campaign() {
    }

    public Campaign(String name, String goal) {
        this.name = name;
        this.goal = goal;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(double totalDonations) {
        this.totalDonations = totalDonations;
    }

    public Set<DonorCampaign> getDonorCampaigns() {
        return donorCampaigns;
    }

    public void setDonorCampaigns(Set<DonorCampaign> donorCampaigns) {
        this.donorCampaigns = donorCampaigns;
    }

    public Set<SponsorCampaign> getSponsorCampaigns() {
        return sponsorCampaigns;
    }

    public void setSponsorCampaigns(Set<SponsorCampaign> sponsorCampaigns) {
        this.sponsorCampaigns = sponsorCampaigns;
    }


}
