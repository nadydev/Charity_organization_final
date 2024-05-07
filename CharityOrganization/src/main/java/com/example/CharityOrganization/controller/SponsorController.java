package com.example.CharityOrganization.controller;
import com.example.CharityOrganization.model.Sponsor.Sponsor;
import com.example.CharityOrganization.service.sponsor.SponsorService;
// import com.example.CharityOrganization.utils.JWT_tokens.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Sponsor")
public class SponsorController {

    @Autowired
    private SponsorService service;

    // @GetMapping("/get-Sponsor")
    // public List<Sponsor> getSponsors(){return service.getSponsors();}


    @PostMapping("/register")
    public ResponseEntity<String> saveSponsor(@RequestBody Sponsor sponsor) {
        try {
            service.addSponsorWithUser(sponsor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sponsor added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add sponsor: " + e.getMessage());
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
    








}