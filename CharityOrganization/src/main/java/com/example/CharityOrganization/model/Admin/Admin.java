package com.example.CharityOrganization.model.Admin;

import jakarta.persistence.*;
import java.util.Set;
import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.EventModel.Event;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "phone_number",nullable = false , unique = true)
    private String phoneNumber;


    @Column(name = "Address",nullable = false , unique = true)
    private String Address;








    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;






    @OneToMany(mappedBy = "admin")
    private Set<Event> events;








    public Admin(){}



    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }




public void setId(int id) {
this.id = id;

}


public Integer getId() {
    return id;

}

public Users getUser() {
   return this.user;
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


public String getAddress() {
    return Address;




}


public void setAddress(String Address) {
this.Address = Address;




}



}