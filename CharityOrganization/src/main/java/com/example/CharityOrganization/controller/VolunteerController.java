package com.example.CharityOrganization.controller;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.service.ManyToManyServices.VolunteerEventService;
import com.example.CharityOrganization.service.Volunteer.VolunteerService;

import com.example.CharityOrganization.utils.DataIntegrityViolationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService service;
    @Autowired
    private VolunteerEventService volunteerEventService;


    @PostMapping("/register")
    public ResponseEntity<String> saveSponsor(@RequestBody Volunteer volunteer) {
        try {
            service.addVolunteerWithUser(volunteer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sponsor added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add sponsor: " + e.getMessage());
        }
    }

    @PostMapping("/participate_on_an_event/{volunteer_id}/{event_id}")
    public String participate_on_an_event(@PathVariable("volunteer_id") Integer volunteerId , @PathVariable("event_id") Integer eventId ) {


        //return volunteerEventService.addVolunteerInEvent(volunteerId, eventId);
        return DataIntegrityViolationHandler.CheckIfVolunteerHasParticipantAlready(() -> volunteerEventService.addVolunteerInEvent(volunteerId , eventId), "You  Particant in Event Successfully", " You are Particant already in This Event");
    }


}

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody Sponsor sponsor) {
    //     Sponsor authenticatedSponsor = service.login(sponsor);
    //     if (authenticatedSponsor != null) {
    //         String accessToken = service.generateAccessToken(authenticatedSponsor.getEmail());
    //         String refreshToken = service.generateRefreshToken(authenticatedSponsor.getEmail());
    //         JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken);
    //         return ResponseEntity.ok(jwtResponse);
    //     } else {
    //         // Check if the email exists in the database
    //         Sponsor existingSponsor = service.findSponsorByEmail(sponsor.getEmail());
    //         if (existingSponsor != null) {
    //             // Email exists, so password must be invalid
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    //         } else {
    //             // Email doesn't exist
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
    //         }
    //     }
    // }
    








