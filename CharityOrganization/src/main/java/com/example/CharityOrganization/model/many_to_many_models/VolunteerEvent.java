package com.example.CharityOrganization.model.many_to_many_models;



import com.example.CharityOrganization.model.EventModel.Event;
import com.example.CharityOrganization.model.Volunteer.Volunteer;

import jakarta.persistence.*;

@Entity

@Table( name = "volunteer_event",uniqueConstraints = @UniqueConstraint(columnNames = {"volunteer_id", "event_id"}))

public class VolunteerEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    // Constructors, getters, and setters
    public VolunteerEvent() {
    }

    public VolunteerEvent(Volunteer volunteer, Event event) {
        this.volunteer = volunteer;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
