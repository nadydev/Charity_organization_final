package com.example.CharityOrganization.model.many_to_many_models;



import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.EventModel.Event;


import jakarta.persistence.*;

@Entity

@Table( name = "donor_event",uniqueConstraints = @UniqueConstraint(columnNames = {"donor_id", "event_id"}))

public class DonorEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "donation")
    private int donation;

    // Constructors, getters, and setters
    public DonorEvent() {
    }

    public DonorEvent(Donor donor, Event event) {
        this.donor = donor;
        this.event = event;
    }
    public DonorEvent(Donor donor, Event event, Integer donation) {
        this.donor = donor;
        this.event = event;
        this.donation = donation ;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    public void setDonation(Integer donation) {
        this.donation = donation;
    }

}
