package com.example.CharityOrganization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//exclude = {SecurityAutoConfiguration.class }
@SpringBootApplication
public class CharityOrganizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharityOrganizationApplication.class, args);
	}

}
