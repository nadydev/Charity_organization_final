package com.example.CharityOrganization.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CharityOrganization.model.Donor.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    @Query("SELECT s FROM Donor s LEFT JOIN FETCH s.user")
    List<Donor> findAllDonorsWithUsers();

    @Query("SELECT s FROM Donor s JOIN FETCH s.user WHERE s.id = :id")
    public Donor findDonorWithUserById(@Param("id") Integer id);

    @Query("SELECT donor FROM Donor donor JOIN FETCH donor.user user WHERE user.username = :username")
    public Donor findDonorWithUserByUsername(@Param("username") String username);



//     @Query("SELECT s FROM Donor s JOIN FETCH s.user WHERE s.id = :userId")

//     public Donor findDonorWithUserByUser_Id(@Param("id") Integer id);

    public Donor findByUserId(Integer userId);

}
