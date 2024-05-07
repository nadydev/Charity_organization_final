package com.example.CharityOrganization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CharityOrganization.model.Volunteer.Volunteer;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    @Query("SELECT s FROM Volunteer s LEFT JOIN FETCH s.user")
    List<Volunteer> findAllVolunteersWithUsers();

    @Query("SELECT s FROM Volunteer s JOIN FETCH s.user WHERE s.id = :id")
    public Volunteer findVolunteerWithUserById(@Param("id") Integer id);
}
