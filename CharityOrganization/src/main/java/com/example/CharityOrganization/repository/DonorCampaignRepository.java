package com.example.CharityOrganization.repository;


import com.example.CharityOrganization.model.Donor.Donor;
import com.example.CharityOrganization.model.EventModel.Event;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.model.many_to_many_models.DonorCampaign;
import com.example.CharityOrganization.model.many_to_many_models.DonorEvent;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;

import java.util.List;
import java.util.Optional;

import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DonorCampaignRepository extends JpaRepository<DonorCampaign, Integer> {



    // @Query("SELECT SUM(d.donation) FROM Donation d WHERE d.donor_id = :donor_id")
    // Long getTotalDonationByDonorId(@Param("donor_id") Integer donor_id);





  /*@Query("SELECT s FROM Volunteer s LEFT JOIN FETCH s.user")
    List<Volunteer> findAllVolunteersWithUsers();

    @Query("SELECT s FROM Volunteer s JOIN FETCH s.user WHERE s.id = :id")
    public Volunteer findVolunteerWithUserById(@Param("id") Integer id);*/
}