package com.example.CharityOrganization.utils;
import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityViolationHandler {
    public static String handleDataIntegrityViolation(Executable action, String entityName, String violatedAttribute) {
        try {
            action.execute();
            return entityName + " added successfully";
        } catch (DataIntegrityViolationException e) {
            String errorMessage;
            if (violatedAttribute != null && !violatedAttribute.isEmpty()) {
                errorMessage = "Error: Duplicate value found for unique attribute '" + violatedAttribute + "' in " + entityName;
            } else {
                errorMessage = "Error: Data integrity violation in " + entityName;
            }
            return errorMessage;
        }
    }

    public static String CheckIfVolunteerHasParticipantAlready(Executable action, String successMessage, String errorMessage) {
        try {
            action.execute();
            // return action;
            return successMessage;
        } catch (DataIntegrityViolationException e) {

            return errorMessage;
        }

    }
}
