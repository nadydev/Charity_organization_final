package com.example.CharityOrganization.repository;


import com.example.CharityOrganization.model.EventModel.Event;
import com.example.CharityOrganization.model.Volunteer.Volunteer;
import com.example.CharityOrganization.model.many_to_many_models.VolunteerEvent;

import java.util.List;
import java.util.Optional;

import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VolunteerEventRepository extends JpaRepository<VolunteerEvent, Integer> {

    List<VolunteerEvent> findByEvent_id(Integer event_id);
    List<VolunteerEvent> findByVolunteer_id(Integer volunteer_id);
    List<VolunteerEvent> findByEvent_idAndVolunteer_id(Integer volunteer_id, Integer event_id);


    boolean existsByVolunteerAndEvent(Volunteer volunteer, Event event);



  /*@Query("SELECT s FROM Volunteer s LEFT JOIN FETCH s.user")
    List<Volunteer> findAllVolunteersWithUsers();

    @Query("SELECT s FROM Volunteer s JOIN FETCH s.user WHERE s.id = :id")
    public Volunteer findVolunteerWithUserById(@Param("id") Integer id);*/
}