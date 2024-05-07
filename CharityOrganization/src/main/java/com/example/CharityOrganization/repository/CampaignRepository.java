package com.example.CharityOrganization.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CharityOrganization.model.Campaign.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign , Integer> {

}
