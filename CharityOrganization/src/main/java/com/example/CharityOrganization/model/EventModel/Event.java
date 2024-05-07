package com.example.CharityOrganization.model.EventModel;
import com.example.CharityOrganization.model.Admin.Admin;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false , unique = true)
    private String name;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "start_date",nullable = false)
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "max_number_of_participants")
    private Long number_of_participants;
    @Column(name  = "total_number_of_participants" )
    private Long total_number_of_participants;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VolunteerEvent> volunteerEvents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    public Event(int id, String name, String location, String start_date, String end_date, Long number_of_participants, Admin admin ,Long total_number_of_participants) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.number_of_participants = number_of_participants;
        this.admin = admin;
        this.total_number_of_participants = total_number_of_participants;
    }

   /*  public Event(int id, String name, String location, String start_date, String end_date, Long number_of_participants) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.number_of_participants = number_of_participants;
    }*/

    public Event() {}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Long getNumber_of_participants() {
        return number_of_participants;
    }

    public void setNumber_of_participants(Long number_of_participants) {
        this.number_of_participants = number_of_participants;
    }

    public Set<VolunteerEvent> getVolunteerEvents() {
        return volunteerEvents;
    }

    public void setVolunteerEvents(Set<VolunteerEvent> volunteerEvents) {
        this.volunteerEvents = volunteerEvents;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getMax_Number_of_participants() {
        return total_number_of_participants;
    }

    public void setMax_Number_of_participants(Long max_Number_of_participants) {

        this.total_number_of_participants = max_Number_of_participants;
    }
}
