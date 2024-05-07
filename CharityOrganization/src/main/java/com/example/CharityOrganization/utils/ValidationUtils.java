package com.example.CharityOrganization.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public boolean isValidString(String value) {
    return value != null && !value.trim().isEmpty() && value.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
    }


    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(regex);
    }


    public boolean isValidPhoneNumber(String phoneNumber) { 
        if (phoneNumber == null || (phoneNumber.length() != 11 && phoneNumber.length() != 13)) {
            return false; // Phone number must be either 11 or 13 characters long
        }
        
        if (phoneNumber.length() == 13 && !phoneNumber.startsWith("+20")) {
            return false; // If the phone number is 13 digits long, it must start with "+20"
        }
        
        // Check if all characters are digits
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false; // Non-digit character found
            }
        }
        
        return true; 
    }
    


}