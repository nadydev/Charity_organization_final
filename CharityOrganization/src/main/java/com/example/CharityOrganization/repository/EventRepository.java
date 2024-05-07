package com.example.CharityOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CharityOrganization.model.EventModel.Event;

public interface EventRepository extends JpaRepository<Event , Integer> {

}
