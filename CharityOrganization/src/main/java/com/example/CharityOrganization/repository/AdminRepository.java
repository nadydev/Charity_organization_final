package com.example.CharityOrganization.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CharityOrganization.model.Admin.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT a FROM Admin a LEFT JOIN FETCH a.user")
    List<Admin> findAllAdminsWithUsers();
    
    @Query("SELECT a FROM Admin a JOIN FETCH a.user WHERE a.id = :id")
    public Admin findAdminWithUserById(@Param("id") Integer id);
    
}

