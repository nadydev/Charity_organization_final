package com.example.CharityOrganization.service.ManyToManyServices;

import java.util.List;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CharityOrganization.model.many_to_many_models.DonorCampaign;
import com.example.CharityOrganization.model.many_to_many_models.DonorEvent;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;
import com.example.CharityOrganization.repository.CampaignRepository;
import com.example.CharityOrganization.repository.DonorCampaignRepository;
import com.example.CharityOrganization.repository.DonorEventRepository;
import com.example.CharityOrganization.repository.DonorRepository;
import com.example.CharityOrganization.repository.EventRepository;
import com.example.CharityOrganization.repository.VolunteerEventRepository;
import com.example.CharityOrganization.repository.VolunteerRepository;
import com.example.CharityOrganization.utils.ValidationUtils;
import com.example.CharityOrganization.model.Campaign.Campaign;
import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.EventModel.Event;
import  com.example.CharityOrganization.model.Volunteer.Volunteer;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import jakarta.transaction.Transactional;
@Service
public class DonorCampaignServices {

  @Autowired
  private DonorRepository donorRepository;

  @Autowired
  private DonorCampaignRepository donorCampaignRepositry;

  @Autowired 
  private CampaignRepository campaignRepository;















  @Transactional 
  public String addDonorInCampaign(Integer donor_id,Integer campaign_id , Integer donation){

     //check for legal Event and volunteer 

   //  if(!CheckIsExistedBefore(volunteerid , eventid)){

       
            Campaign campaign = campaignRepository.findById(campaign_id).orElseThrow(() -> new IllegalArgumentException("Campaign not found"));
           // campaign.setMax_Number_of_participants(event.getMax_Number_of_participants() + 1L );

            Donor donor = donorRepository.findById(donor_id).orElseThrow(() -> new IllegalArgumentException("Donor not found"));
       //saving donation 
            donor.setTotalDonation(donor.getTotalDonation() + donation);
      // deposite the donation in the campaign
            campaign.setTotalDonations(campaign.getTotalDonations() + donation);

            //Many to many mapping
            DonorCampaign donorCampaign = new DonorCampaign(donor,campaign,donation);
          
            donorRepository.save(donor);
            donorCampaignRepositry.save(donorCampaign);
            campaignRepository.save(campaign);
            return "success  ";

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
