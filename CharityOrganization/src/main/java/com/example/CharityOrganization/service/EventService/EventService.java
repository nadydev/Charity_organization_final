package com.example.CharityOrganization.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CharityOrganization.model.EventModel.Event;
import com.example.CharityOrganization.model.EventModel.EventDTO;
import com.example.CharityOrganization.repository.EventRepository;
import com.example.CharityOrganization.utils.encryption.SingleDESEncryption;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Add other required autowired repositories

    @Transactional
    public void addEvent(Event event) {
        if (!isValidEvent(event)) {
            throw new IllegalArgumentException("Invalid event data.");
        }
        try {
            // Encrypt event data if needed
            String encryptedLocation = SingleDESEncryption.encrypt(event.getLocation());
            String encryptedStartDate = SingleDESEncryption.encrypt(event.getStart_date());
            String encryptedEndDate = SingleDESEncryption.encrypt(event.getEnd_date());

            event.setName(event.getName());
            event.setLocation(encryptedLocation);
            event.setStart_date(encryptedStartDate);
            event.setEnd_date(encryptedEndDate);
            event.setNumber_of_participants(event.getNumber_of_participants());
            event.setMax_Number_of_participants(0L);

            // Save the event
            eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace(); // Handle encryption error
        }
    }

    public List<EventDTO> getAllEventsWithDecryptedData() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> decryptedEvents = new ArrayList<>();
        for (Event event : events) {
            // Decrypt event data if needed
            try {
                String decryptedLocation = SingleDESEncryption.decrypt(event.getLocation());
                String decryptedStartDate = SingleDESEncryption.decrypt(event.getStart_date());
                String decryptedEndDate = SingleDESEncryption.decrypt(event.getEnd_date());

                // Create EventDTO object with decrypted data
                EventDTO eventDTO = new EventDTO();
                eventDTO.setEvent_id(event.getId());
                eventDTO.setDecryptedName(event.getName());
                eventDTO.setDecryptedLocation(decryptedLocation);
                eventDTO.setDecryptedStart_date(decryptedStartDate);
                eventDTO.setDecryptedEnd_date(decryptedEndDate);
                eventDTO.setNumber_of_participants(event.getNumber_of_participants());
                eventDTO.setMax_Number_of_participants(event.getMax_Number_of_participants());

                // Add to the list of decrypted events
                decryptedEvents.add(eventDTO);
            } catch (Exception e) {
                e.printStackTrace(); // Handle decryption error
            }
        }
        return decryptedEvents;
    }

    @Transactional
    public void updateEvent(int eventId, Event updatedEvent) {
        if (!isValidEvent(updatedEvent)) {
            throw new IllegalArgumentException("Invalid event data.");
        }
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            try {
                // Encrypt updated event data if needed
                String encryptedLocation = SingleDESEncryption.encrypt(updatedEvent.getLocation());
                String encryptedStartDate = SingleDESEncryption.encrypt(updatedEvent.getStart_date());
                String encryptedEndDate = SingleDESEncryption.encrypt(updatedEvent.getEnd_date());
    
                // Set encrypted data to the event object
                event.setName(updatedEvent.getName()); // Update name
                event.setLocation(encryptedLocation);
                event.setStart_date(encryptedStartDate);
                event.setEnd_date(encryptedEndDate);
                event.setNumber_of_participants(updatedEvent.getNumber_of_participants()); // Update number of participants
    
                eventRepository.save(event);
            } catch (Exception e) {
                e.printStackTrace(); // Handle encryption error
            }
        } else {
            throw new IllegalArgumentException("Event not found with ID: " + eventId);
        }
    }
    

    @Transactional
    public String deleteEvent(int eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
            return "Event deleted successfully";
        } else {
            return "Event not found";
        }
    }


    public EventDTO getEventById(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.map(event -> {
            try {
                String decryptedLocation = SingleDESEncryption.decrypt(event.getLocation());
                String decryptedStartDate = SingleDESEncryption.decrypt(event.getStart_date());
                String decryptedEndDate = SingleDESEncryption.decrypt(event.getEnd_date());

                EventDTO eventDTO = new EventDTO();
                eventDTO.setEvent_id(event.getId());
                eventDTO.setDecryptedName(event.getName());
                eventDTO.setDecryptedLocation(decryptedLocation);
                eventDTO.setDecryptedStart_date(decryptedStartDate);
                eventDTO.setDecryptedEnd_date(decryptedEndDate);
                eventDTO.setNumber_of_participants(event.getNumber_of_participants()); 


                return eventDTO;
            } catch (Exception e) {
                throw new RuntimeException("Failed to decrypt event data for event ID: " + eventId, e);
            }
        }).orElse(null); // If the event is not found, return null
    }



    private boolean isValidEvent(Event event) {
        return event != null &&
                event.getName() != null && !event.getName().isEmpty() &&
                event.getLocation() != null && !event.getLocation().isEmpty() &&
                event.getStart_date() != null && !event.getStart_date().isEmpty() &&
                event.getEnd_date() != null && !event.getEnd_date().isEmpty();
    }
}