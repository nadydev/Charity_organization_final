package com.example.CharityOrganization.service.ManyToManyServices;

import java.util.List;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CharityOrganization.model.many_to_many_models.DonorEvent;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;
import com.example.CharityOrganization.repository.DonorEventRepository;
import com.example.CharityOrganization.repository.DonorRepository;
import com.example.CharityOrganization.repository.EventRepository;
import com.example.CharityOrganization.repository.VolunteerEventRepository;
import com.example.CharityOrganization.repository.VolunteerRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.EventModel.Event;
import  com.example.CharityOrganization.model.Volunteer.Volunteer;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import jakarta.transaction.Transactional;
@Service
public class DonorEventServices {

  @Autowired
  private DonorRepository donorRepository;

  @Autowired
  private DonorEventRepository donorEventRepositry;

  @Autowired 
  private EventRepository eventRepository;

  @Autowired
  private ValidationUtils validation;
  @Autowired 
  private PasswordEncoder passwordEncoder;

//   public String addVolunteerInEvent(Integer volunteerId, Integer eventId) {
//     // Check if the event exists and is active
//     Event event = eventRepository.findById(eventId)
//                                   .orElseThrow(() -> new IllegalArgumentException("Event not found or inactive"));

//     // Check if the volunteer exists and is eligible
//     Volunteer volunteer = volunteerRepository.findById(volunteerId)
//                                               .orElseThrow(() -> new IllegalArgumentException("Volunteer not found or ineligible"));

//     // Optionally, check if the volunteer is not already registered for the event
//    /*  if (volunteerEventRepositry.existsByVolunteerAndEvent(volunteer, event)) {
//         return "You are already registered for this event";
//     }
// */
//     // If all checks pass, add the volunteer to the event
//     VolunteerEvent volunteerEvent = new VolunteerEvent(volunteer, event);
//     volunteerEventRepositry.save(volunteerEvent);
    
//     return "Volunteer successfully added to the event";
// }







@Transactional 
  public String addDonorInEvent(Integer donor_id,Integer event_id){

     //check for legal Event and volunteer 

   //  if(!CheckIsExistedBefore(volunteerid , eventid)){

       
            Event event = eventRepository.findById(event_id).orElseThrow(() -> new IllegalArgumentException("Event not found"));
             event.setMax_Number_of_participants(event.getMax_Number_of_participants() + 1L );

            Donor donor = donorRepository.findById(donor_id).orElseThrow(() -> new IllegalArgumentException("Donor not found"));
       
            DonorEvent donorEvent = new DonorEvent(donor,event);
            donorEventRepositry.save(donorEvent);
            eventRepository.save(event);
            return "success lol event";

   //  }

     //return "You are Participated already";


  }


  @Transactional 
  public String addDonorInEventWithDonation(Integer donor_id,Integer event_id , Integer donation){

     //check for legal Event and volunteer 

   //  if(!CheckIsExistedBefore(volunteerid , eventid)){

       
            Event event = eventRepository.findById(event_id).orElseThrow(() -> new IllegalArgumentException("Event not found"));
             event.setMax_Number_of_participants(event.getMax_Number_of_participants() + 1L );

            Donor donor = donorRepository.findById(donor_id).orElseThrow(() -> new IllegalArgumentException("Donor not found"));
       //saving donation
            donor.setTotalDonation(donor.getTotalDonation() + donation);

            DonorEvent donorEvent = new DonorEvent(donor,event,donation);
          
            donorRepository.save(donor);
            donorEventRepositry.save(donorEvent);
            eventRepository.save(event);
            return "success lol event";

   //  }

     //return "You are Participated already";


  }


  // public Long getTotalDonation(Integer donorId) {
  //       return donorEventRepositry.getTotalDonationByDonorId(donorId);
  // }










//   private boolean CheckIsExistedBefore(Integer volunteer_id , Integer event_id) {
//     //List<VolunteerEvent> volunteerlist= volunteerEventRepositry.findByVolunteer_id(volunteer_id);
//     List<VolunteerEvent> volunteer_event_list= volunteerEventRepositry.findByEvent_id(event_id);

//   //     if(volunteer_event_list.size() > 0 && volunteerlist.size() > 0)
//   //           return true;
//   // return false;

//   List<VolunteerEvent> volunteerEventList = volunteerEventRepositry.findByEvent_idAndVolunteer_id(volunteer_id, event_id);
//   return  !volunteerEventList.isEmpty(); 
// }

}
