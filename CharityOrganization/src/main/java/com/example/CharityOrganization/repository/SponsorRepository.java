package com.example.CharityOrganization.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CharityOrganization.model.Sponsor.Sponsor;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor,Integer> {
    
    @Query("SELECT s FROM Sponsor s")
    List<Sponsor> findAllSponsors();   

    @Query("SELECT s FROM Sponsor s JOIN FETCH s.user WHERE s.id = :id")
    public Sponsor findSponsorWithUserById(@Param("id") Integer id);

}