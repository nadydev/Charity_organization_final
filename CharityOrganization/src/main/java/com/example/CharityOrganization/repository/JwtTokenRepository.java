package com.example.CharityOrganization.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CharityOrganization.model.JwtToken;
import com.example.CharityOrganization.model.Donor.Donor;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {

    void deleteByUsername(String username);
    JwtToken findByUsername(String username);
    JwtToken findByUsernameAndType(String username, String type);

    @Query("SELECT t.user_id FROM JwtToken t WHERE t.token = :token AND t.type = :type")
    Integer findIdByTokenAndType(@Param("token") String token, @Param("type") String type);




    public JwtToken findUsernameByTokenAndType(String token , String type);
    public Integer findUser_idByTokenAndType(String token , String type);



}
