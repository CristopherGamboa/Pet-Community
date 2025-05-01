package com.petcommunity.pet_community.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.petcommunity.pet_community.enums.EventType;
import com.petcommunity.pet_community.enums.PetType;
import com.petcommunity.pet_community.models.Event;
import com.petcommunity.pet_community.services.EventService;
import com.petcommunity.pet_community.services.EventServiceTest;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceTest.class);

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Controller findAll returns HATEOAS structure")
    public void findAllTest() {
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

        when(eventService.findAll()).thenReturn(List.of(event));

        CollectionModel<EntityModel<Event>> result = eventController.findAll();

        assertNotNull(result);
        assertEquals(1, result.getContent().size());

        EntityModel<Event> eventModel = result.getContent().iterator().next();
        assertEquals("Pet Fest", eventModel.getContent().getName());

        assertTrue(eventModel.getLinks().hasLink("self"));
        assertTrue(result.getLinks().hasLink("events"));
    }

    @Test
    @DisplayName("Controller findById returns entity with HATEOAS links")
    public void findByIdTest() {
        Long id = 1L;
        Event event = Event.builder()
            .id(id)
            .name("Pet Fest")
            .description("Pet festival for dogs")
            .location("San Francisco")
            .eventType(EventType.FAIR)
            .petType(PetType.DOG)
            .startDate(LocalDateTime.now().plusDays(3))
            .endDate(LocalDateTime.now().plusDays(4))
            .build();

        when(eventService.findById(id)).thenReturn(Optional.of(event));

        EntityModel<Event> result = eventController.findById(id);

        assertNotNull(result);
        assertEquals("Pet Fest", result.getContent().getName());

        assertTrue(result.getLinks().hasLink("self"));
        assertTrue(result.getLinks().hasLink("events"));
    }
}
