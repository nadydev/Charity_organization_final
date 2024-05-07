package com.example.CharityOrganization.controller;

import com.example.CharityOrganization.model.Campaign.Campaign;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.Donor.DonorDTO;
import com.example.CharityOrganization.model.EventModel.Event;
import com.example.CharityOrganization.model.EventModel.EventDTO;
import com.example.CharityOrganization.model.Sponsor.Sponsor;
import com.example.CharityOrganization.model.Sponsor.SponsorDTO;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.model.Volunteer.VolunteerDTO;
import com.example.CharityOrganization.service.Campaign.CampaignService;
import com.example.CharityOrganization.service.Donor.DonorService;
import com.example.CharityOrganization.service.EventService.EventService;
import com.example.CharityOrganization.service.Volunteer.VolunteerService;
import com.example.CharityOrganization.service.sponsor.SponsorService;
import com.example.CharityOrganization.utils.DataIntegrityViolationHandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    EventService eventService;

    @Autowired
    CampaignService campaignService;

    @Autowired
    DonorService donorService;

    @Autowired 
    SponsorService sponsorService;

    @Autowired 
    VolunteerService volunteerService;



    @PostMapping("/add_Event")
    public String addEvent(@RequestBody Event event) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> eventService.addEvent(event), "Event", null);
    }

    @DeleteMapping("/delete_event/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id);
    }

    @PutMapping("/update_event/{id}")
    public String updateEvent(@PathVariable("id") Integer eventId, @RequestBody Event updatedEvent) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> eventService.updateEvent(eventId, updatedEvent), "Event", null);
    }

    @GetMapping("/ManageEvents")
    public List<EventDTO> getEvents() {
        return eventService.getAllEventsWithDecryptedData();
    }

    @GetMapping("/event_by_id/{id}")
    public Object getEventById(@PathVariable Integer id) {
        EventDTO event = eventService.getEventById(id);
        return event != null ? event : "Event not found with id: " + id;
    }

    @PostMapping("/add_campaign")
    public String addCampaign(@RequestBody Campaign campaign) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> campaignService.add(campaign), "Campaign", "name");
    }

    @DeleteMapping("/delete_campaign/{id}")
    public String deleteCampaign(@PathVariable int id) {
        return campaignService.delete(id);
    }

    @PutMapping("/update_campaign/{id}")
    public String updateCampaign(@PathVariable("id") Integer campaignId, @RequestBody Campaign updatedCampaign) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> campaignService.updateCampaign(campaignId, updatedCampaign), "Campaign", "name");
    }

    @GetMapping("/manage_campaigns")
    public List<Campaign> getCampaigns() {
        return campaignService.getAll();
    }

    @GetMapping("/campaign_by_id/{id}")
    public Object getCampaignById(@PathVariable Integer id) {
        Campaign campaign = campaignService.getById(id);
        return campaign != null ? campaign : "Campaign not found with id: " + id;
    }

    @PostMapping("/add_donor")
    public String addDonor(@RequestBody Donor donor) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> donorService.addDonorWithUser(donor), "Donor", "email or Phone Number");
    }

    @DeleteMapping("/delete_donor/{id}")
    public String deleteDonor(@PathVariable int id) {
        return donorService.deleteDonor(id);
    }

    @PutMapping("/update_donor/{id}")
    public String updateDonor(@PathVariable("id") Integer donorId, @RequestBody Donor updatedDonor) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> donorService.updateDonor(donorId, updatedDonor), "Donor", "email or phone number");
    }

    @GetMapping("/manage_donors")
    public List<DonorDTO> getDonors() {
        return donorService.getDonorsWithDecryptedData();
    }

    @GetMapping("/donor_by_id/{id}")
    public Object getDonorById(@PathVariable Integer id) {
        DonorDTO donor = donorService.findDonorWithUserAndDecrypt(id);
        return donor != null ? donor : "Donor not found with id: " + id;
    }






     // Sponsor




    @PostMapping("/add_sponsor")
    public String addSponsor(@RequestBody Sponsor sponsor) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> sponsorService.addSponsorWithUser(sponsor), "Sponsor", "email");
    }

    @DeleteMapping("/delete_sponsor/{id}")
    public String deleteSponsor(@PathVariable int id) {
        return sponsorService.deleteSponsor(id);
    }

    @PutMapping("/update_sponsor/{id}")
    public String updateSponsor(@PathVariable("id") Integer sponsorId, @RequestBody Sponsor updatedSponsor) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> sponsorService.updateSponsor(sponsorId, updatedSponsor), "Sponsor", "email");
    }

    @GetMapping("/manage_sponsors")
    public List<SponsorDTO> getSponsors() {
        return sponsorService.getSponsorsWithDecryptedData();
    }

    @GetMapping("/sponsor_by_id/{id}")
    public Object getSponsorById(@PathVariable Integer id) {
        SponsorDTO sponsor = sponsorService.findSponsorWithUserAndDecrypt(id);
        return sponsor != null ? sponsor : "Sponsor not found with id: " + id;
    }














    // Volunteer

    @PostMapping("/add_volunteer")
    public String addVolunteer(@RequestBody Volunteer volunteer) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> volunteerService.addVolunteerWithUser(volunteer), "Volunteer", "email or address");
    }

    @DeleteMapping("/delete_volunteer/{id}")
    public String deleteVolunteer(@PathVariable int id) {
        return volunteerService.deleteVolunteer(id);
    }

    @PutMapping("/update_volunteer/{id}")
    public String updateVolunteer(@PathVariable("id") Integer volunteerId, @RequestBody Volunteer updatedVolunteer) {
        return DataIntegrityViolationHandler.handleDataIntegrityViolation(() -> volunteerService.updateVolunteer(volunteerId, updatedVolunteer), "Volunteer", "email or address");
    }

    @GetMapping("/manage_volunteers")
    public List<VolunteerDTO> getVolunteers() {
        return volunteerService.getVolunteersWithDecryptedData();
    }

    @GetMapping("/volunteer_by_id/{id}")
    public Object getVolunteerById(@PathVariable Integer id) {
        VolunteerDTO volunteer = volunteerService.findVolunteerWithUserAndDecrypt(id);
        return volunteer != null ? volunteer : "Volunteer not found with id: " + id;
    }
}












