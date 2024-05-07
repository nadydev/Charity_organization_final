package com.example.CharityOrganization.model.EventModel;

public class EventDTO {
    private int event_id;
    private String DecryptedName;
    private String DecryptedLocation;
    private String DecryptedStart_date;
    private String DecryptedEnd_date;
    private Long numberOfParticipants;
    private Long totalnumberOfParticipants;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }


    public Long getNumber_of_participants() {
        return numberOfParticipants;
    }
    public Long getMax_Number_of_participants() {
        return totalnumberOfParticipants;
    }

    public void setNumber_of_participants(Long mumParticipants) {
        this.numberOfParticipants =   mumParticipants;
    }


    public String getDecryptedName() {
        return DecryptedName;
    }

    public void setDecryptedName(String DecryptedName) {
        this.DecryptedName = DecryptedName;
    }

    public String getDecryptedLocation() {
        return DecryptedLocation;
    }

    public void setDecryptedLocation(String DecryptedLocation) {
        this.DecryptedLocation = DecryptedLocation;
    }

    public String getDecryptedStart_date() {
        return DecryptedStart_date;
    }

    public void setDecryptedStart_date(String DecryptedStart_date) {
        this.DecryptedStart_date = DecryptedStart_date;
    }

    public String getDecryptedEnd_date() {
        return DecryptedEnd_date;
    }

    public void setDecryptedEnd_date(String DecryptedEnd_date) {
        this.DecryptedEnd_date = DecryptedEnd_date;
    }

    public void setMax_Number_of_participants(Long max_Number_of_participants) {
        // TODO Auto-generated method stub
        this.totalnumberOfParticipants = max_Number_of_participants;
    }
}
