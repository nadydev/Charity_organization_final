package com.example.CharityOrganization.controller;

import com.example.CharityOrganization.model.Users;
import com.example.CharityOrganization.model.UsersDTO;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.Donor.DonorDTO;
import com.example.CharityOrganization.model.EventModel.EventDTO;
import com.example.CharityOrganization.model.Sponsor.Sponsor;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.service.Donor.DonorService;

import com.example.CharityOrganization.service.UpdateUser.UsersService;
import com.example.CharityOrganization.service.Volunteer.VolunteerService;
import com.example.CharityOrganization.utils.DataIntegrityViolationHandler;

import org.apache.catalina.User;
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


@RestController
@RequestMapping("Profile")
public class UserProfileController {




    @Autowired
    private UsersService userService;


    //  @GetMapping("/show/{id}")
    // public UsersDTO ShowProfile(@PathVariable("id") Integer profileId) throws Exception {
    //    // return service.updateVolunteerWithUser(volunteerId,updatedVolunteer);
    //     return  userService.findUserAndDecrypt(profileId);
    // }

    @GetMapping("/show/{id}")
    public Object ShowProfile(@PathVariable("id") Integer profileId) throws Exception {
        UsersDTO user = userService.findUserAndDecrypt(profileId);
        return user != null ? user : "user not found with id: " + profileId;
    }

    @PutMapping("/update/{id}")
    public String UpdateProfileUser(@PathVariable("id") Integer  id, @RequestBody Users user) throws Exception {


        userService.updateUser(id, user);

        return "done";
    }
}
