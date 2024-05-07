package com.example.CharityOrganization.service.Campaign;

import com.example.CharityOrganization.model.Campaign.Campaign;
import com.example.CharityOrganization.repository.CampaignRepository;
import com.example.CharityOrganization.service.GenericCrudService;
import com.example.CharityOrganization.utils.ValidationUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService extends GenericCrudService<Campaign> {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    private ValidationUtils validation;

    @Override
    protected CampaignRepository getRepository() {
        return repository;
    }

    @Override
    public void add(Campaign campaign) {
        if (isValidCampaign(campaign)) {
            repository.save(campaign);
        } else {
            throw new IllegalArgumentException("Invalid campaign data check that data not empty or text without numbers");
        }
    }

    private boolean isValidCampaign(Campaign campaign) {
        return campaign != null &&
            validation.isValidString(campaign.getName()) &&
            validation.isValidString(campaign.getGoal());
    }

    @Override
    public List<Campaign> getAll() {
        return repository.findAll();
    }

    @Override
    public Campaign getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public String delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Unfound ID";
        }
    }

    public String updateCampaign(Integer campaignId, Campaign updatedCampaign) {
        Campaign existingCampaign = repository.findById(campaignId).orElse(null);
        if (existingCampaign != null) {
            existingCampaign.setName(updatedCampaign.getName());
            existingCampaign.setGoal(updatedCampaign.getGoal());
            if (isValidCampaign(existingCampaign)) {
                repository.save(existingCampaign);
                return "Updated successfully";
            } else {
                return "Invalid campaign data should be stringified or not empty";
            }
        } else {
            return "Entity with ID " + campaignId + " not found ";
        }
    }
    
    }

