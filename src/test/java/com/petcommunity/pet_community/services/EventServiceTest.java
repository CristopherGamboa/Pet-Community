package com.petcommunity.pet_community.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.petcommunity.pet_community.dtos.EventRequest;
import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.repositories.interfaces.IEventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceTest.class);

    @InjectMocks
    private EventService eventService;

    @Mock
    private IEventRepository eventRepository;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Service save event test")
    public void saveEventTest() {
        EventRequest request = EventRequest.builder()
            .name("Pet Fest")
            .description("Pet festival for dogs")
            .location("San Francisco")
            .eventType(EventType.FAIR)
            .petType(PetType.DOG)
            .startDate(LocalDateTime.now().plusDays(3))
            .endDate(LocalDateTime.now().plusDays(4))
            .build();

        Event mappedEvent = Event.builder()
            .name(request.getName())
            .description(request.getDescription())
            .location(request.getLocation())
            .eventType(request.getEventType())
            .petType(request.getPetType())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();

        
        when(eventRepository.save(mappedEvent)).thenReturn(mappedEvent);

        Event savedEvent = eventService.save(request).get();

        assertNotNull(savedEvent);
        assertEquals(mappedEvent.getName(), savedEvent.getName());
        assertEquals(mappedEvent.getDescription(), savedEvent.getDescription());
        assertEquals(mappedEvent.getLocation(), savedEvent.getLocation());
        assertEquals(mappedEvent.getEventType(), savedEvent.getEventType());
        assertEquals(mappedEvent.getPetType(), savedEvent.getPetType());
        assertEquals(mappedEvent.getStartDate(), savedEvent.getStartDate());
        assertEquals(mappedEvent.getEndDate(), savedEvent.getEndDate());
    }

    @Test
    @DisplayName("Service update event test")
    public void updateEventTest() {
        Long id = 1L;
        EventRequest request = EventRequest.builder()
            .name("Pet Fest")
            .description("Pet festival for dogs")
            .location("San Francisco")
            .eventType(EventType.FAIR)
            .petType(PetType.DOG)
            .startDate(LocalDateTime.now().plusDays(3))
            .endDate(LocalDateTime.now().plusDays(4))
            .build();

        Event mappedEvent = Event.builder()
            .id(id)
            .name(request.getName())
            .description(request.getDescription())
            .location(request.getLocation())
            .eventType(request.getEventType())
            .petType(request.getPetType())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();

        when(eventRepository.save(mappedEvent)).thenReturn(mappedEvent);
        when(eventRepository.existsById(id)).thenReturn(true);

        Event savedEvent = eventService.update(id, request).get();

        assertNotNull(savedEvent);
        assertEquals(mappedEvent.getName(), savedEvent.getName());
        assertEquals(mappedEvent.getDescription(), savedEvent.getDescription());
        assertEquals(mappedEvent.getLocation(), savedEvent.getLocation());
        assertEquals(mappedEvent.getEventType(), savedEvent.getEventType());
        assertEquals(mappedEvent.getPetType(), savedEvent.getPetType());
        assertEquals(mappedEvent.getStartDate(), savedEvent.getStartDate());
        assertEquals(mappedEvent.getEndDate(), savedEvent.getEndDate());
    }
}
