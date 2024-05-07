package com.example.CharityOrganization.controller;

import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.Donor.DonorDTO;
import com.example.CharityOrganization.model.Sponsor.Sponsor;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.model.many_to_many_models.DonorCampaign;
import com.example.CharityOrganization.service.Donor.DonorService;
import com.example.CharityOrganization.service.ManyToManyServices.DonorCampaignServices;
import com.example.CharityOrganization.service.ManyToManyServices.DonorEventServices;
import com.example.CharityOrganization.service.Volunteer.VolunteerService;
import com.example.CharityOrganization.utils.DataIntegrityViolationHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("Donor")
public class DonorController {


    @Autowired
    DonorService service;




    // @Autowired
    // private VolunteerService  Volunteerservice;

    @Autowired
    private DonorEventServices  donorEventService;

    @Autowired
    private DonorCampaignServices donorCampaignServices;


    @PostMapping("/register")
    public ResponseEntity<String> saveDonor(@RequestBody Donor donor) {
        try {
            service.addDonorWithUser(donor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Donor added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add donor: " + e.getMessage());
        }
    }


    @GetMapping("/show_donor_profile/{id}")
    public DonorDTO ShowDonorProfile(@PathVariable("id") Integer DonorId) {
        // return service.updateVolunteerWithUser(volunteerId,updatedVolunteer);
        return  service.findDonorWithUser_IdAndDecrypt(DonorId);
    }

    @PutMapping("updateDonorById/{id}")
    public String UpdateDonorProfile(@PathVariable("id") Integer  id, @RequestBody Donor donor) {


        service.updateDonor(id, donor);

        return "done";
    }


/// events

    @PostMapping("/participate_on_an_event/{donor_id}/{event_id}/{donation}")
    public String participate_on_an_event_with_donation(@PathVariable("donor_id") Integer donorId , @PathVariable("event_id") Integer eventId , @PathVariable("donation") Integer donation) {

        //return volunteerEventService.addVolunteerInEvent(volunteerId, eventId);
        return DataIntegrityViolationHandler.CheckIfVolunteerHasParticipantAlready(() -> donorEventService.addDonorInEventWithDonation(donorId , eventId , donation), "You  Particant in Event Successfully", " You are Particant already in This Event");
    }

    @PostMapping("/participate_on_an_event/{donor_id}/{event_id}")
    public String participate_on_an_event(@PathVariable("donor_id") Integer donorId, @PathVariable("event_id") Integer eventId ) {

        //return volunteerEventService.addVolunteerInEvent(volunteerId, eventId);
        return DataIntegrityViolationHandler.CheckIfVolunteerHasParticipantAlready(() -> donorEventService.addDonorInEvent(donorId , eventId), "You  Particant in Event Successfully", " You are Particant already in This Event");
    }



    /// Campaigns

    @PostMapping("/participate_on_an_campaign/{donor_id}/{campaign_id}/{donation}")
    public String participate_on_an_campaign(@PathVariable("donor_id") Integer donorId , @PathVariable("campaign_id") Integer campaignId , @PathVariable("donation") Integer donation) {

        //return volunteerEventService.addVolunteerInEvent(volunteerId, eventId);
        return DataIntegrityViolationHandler.CheckIfVolunteerHasParticipantAlready(() -> donorCampaignServices.addDonorInCampaign(donorId , campaignId , donation), "You  Particant in Event Successfully", " You are Particant already in This Event");
    }

    // @GetMapping("/get_total_donation/{donor_id}")
    // public Long getTotalDonatioInteger(@PathVariable("donor_id") Integer donorId) {
    //     return  service.getTotalDonation(donorId);

    // }


 /*   @PutMapping("updateDonorById/{id}")
    public String UpdateDonorProfileUser(@PathVariable("id") Integer  id, @RequestBody Donor donor) {


        userService.updateDonor(id, donor);

         return "done";
    }*/
}