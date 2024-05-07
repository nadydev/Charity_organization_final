package com.example.CharityOrganization.model.Donor;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.many_to_many_models.DonorCampaign;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "donor")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // @Column(name = "name", nullable = false)
    //private String name;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;


    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "total_donation")
    private Long total_donation;

    @JsonIgnore
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DonorCampaign> donorCampaigns = new HashSet<>();



    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;


    public Donor() {
    }

    public Donor( String birthDate) {
        this.birthDate = birthDate;
    }



    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTotalDonation(){
        if(total_donation==null)
            total_donation = 0L;
        return total_donation;
    }

    public void setTotalDonation(Long total_donation){
        this.total_donation= total_donation;
    }








    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }




    public void setUser(Users user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<DonorCampaign> getDonorCampaigns() {
        return donorCampaigns;
    }

    public void setDonorCampaigns(Set<DonorCampaign> donorCampaigns) {
        this.donorCampaigns = donorCampaigns;
    }
    public Users getUser() {
        return user;
    }
}
