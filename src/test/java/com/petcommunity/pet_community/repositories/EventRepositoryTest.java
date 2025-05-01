package com.petcommunity.pet_community.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.repositories.interfaces.IEventRepository;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(EventRepositoryTest.class);
    
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
    @DisplayName("Repository save event test")
    public void saveEventTest() {
        Event event = Event.builder()
            .id(1L)
            .name("Pet Fest")
            .description("Pet festival for dogs")
            .location("San Francisco")
            .eventType(EventType.FAIR)
            .petType(PetType.DOG)
            .startDate(LocalDateTime.now().plusDays(3))
            .endDate(LocalDateTime.now().plusDays(4))
            .build();

        when(eventRepository.save(event)).thenReturn(event);
        Event savedEvent = eventRepository.save(event);

        assertNotNull(savedEvent.getId());
        assertEquals(event.getName(), savedEvent.getName());
        assertEquals(event.getDescription(), savedEvent.getDescription());
        assertEquals(event.getLocation(), savedEvent.getLocation());
        assertEquals(event.getEventType(), savedEvent.getEventType());
        assertEquals(event.getPetType(), savedEvent.getPetType());
        assertEquals(event.getStartDate(), savedEvent.getStartDate());
        assertEquals(event.getEndDate(), savedEvent.getEndDate());
    }

    @Test
    @DisplayName("Repository find event by ID test")
    public void findEventByIdTest() {
        Event event = Event.builder()
            .id(1L)
            .name("Pet Fest")
            .description("Pet festival for dogs")
            .location("San Francisco")
            .eventType(EventType.FAIR)
            .petType(PetType.DOG)
            .startDate(LocalDateTime.now().plusDays(3))
            .endDate(LocalDateTime.now().plusDays(4))
            .build();

        when(eventRepository.save(event)).thenReturn(event);
        Event savedEvent = eventRepository.save(event);

        when(eventRepository.findById(savedEvent.getId())).thenReturn(Optional.of(savedEvent));
        Optional<Event> foundEvent = eventRepository.findById(savedEvent.getId());

        assertNotNull(foundEvent);
        assertEquals(savedEvent.getId(), foundEvent.get().getId());
        assertEquals(savedEvent.getName(), foundEvent.get().getName());
    }
}
