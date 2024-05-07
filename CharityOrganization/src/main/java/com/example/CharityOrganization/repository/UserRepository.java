package com.example.CharityOrganization.repository;
import org.springframework.stereotype.Repository;
import com.example.CharityOrganization.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);


}