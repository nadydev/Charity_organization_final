package com.example.CharityOrganization.model.Volunteer;

import jakarta.persistence.*;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VolunteerEvent> volunteerEvents = new HashSet<>();


    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;




    public Volunteer() {}
    public Volunteer(int id , String address) {
        this.id = id;
        this.address = address;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<VolunteerEvent> getVolunteerEvents() {
        return volunteerEvents;
    }

    public void setVolunteerEvents(Set<VolunteerEvent> volunteerEvents) {
        this.volunteerEvents = volunteerEvents;
    }


    public void setUser(Users user) {
        this.user = user;
    }
    
    public Users getUser() {
        return user;
    }
}

