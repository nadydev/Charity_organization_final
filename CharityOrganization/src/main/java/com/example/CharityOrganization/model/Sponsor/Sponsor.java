package com.example.CharityOrganization.model.Sponsor;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.many_to_many_models.SponsorCampaign;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Sponsor")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "company_location",nullable = false , unique= true)
    private String companyLocation;

    @Column(name="business_number",unique = true , nullable = false)
    private String businessNumber;





    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;


    @JsonIgnore
    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SponsorCampaign> sponsorCampaigns = new HashSet<>();

    // Constructors, getters, and setters

    public Sponsor() {
    }

    public Sponsor(Integer Id, String companyLocation, String businessNumber) {
        this.id = Id;
        this.companyLocation = companyLocation;
        this.businessNumber = businessNumber;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public Set<SponsorCampaign> getSponsorCampaigns() {
        return sponsorCampaigns;
    }

    public void setSponsorCampaigns(Set<SponsorCampaign> sponsorCampaigns) {
        this.sponsorCampaigns = sponsorCampaigns;
    }


    public void setUser(Users user) {
        this.user = user;
    }
    
    public Users getUser() {
        return user;
    }
}
