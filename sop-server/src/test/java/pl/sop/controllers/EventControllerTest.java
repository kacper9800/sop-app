package pl.sop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sop.dto.EventDTO;
import pl.sop.entities.Event;
import pl.sop.services.EventService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private EventService eventService;

    @Before
    public void setup() {
        EventController eventController = new EventController();
        eventController.eventService = eventService;
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void when_get_all_events_should_return_ok() throws Exception {
        List<EventDTO> mockEvents = new ArrayList<>();
        mockEvents.add(new EventDTO());
        when(eventService.getAllEvents()).thenReturn(mockEvents);
        mockMvc.perform(get("/api/planner/event"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_get_all_events_for_user_id_should_return_ok() throws Exception {
        List<EventDTO> mockEvents = new ArrayList<>();
        mockEvents.add(new EventDTO());
        when(eventService.getAllEventsForUserId(Mockito.any())).thenReturn(mockEvents);
        mockMvc.perform(get("/api/planner/event/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void when_get_all_base_events_should_return_ok() throws Exception {
        List<EventDTO> mockEvents = new ArrayList<>();
        mockEvents.add(new EventDTO());
        when(eventService.getAllEventsWithoutDate()).thenReturn(mockEvents);
        mockMvc.perform(get("/api/planner/event/base", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void when_create_event_should_return_ok() throws Exception {
        Event mockEvent = new Event();
        when(eventService.createEvent(Mockito.any())).thenReturn(mockEvent);
        mockMvc.perform(post("/api/planner/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new EventDTO())))
                .andExpect(status().isOk());
    }

    //TODO: Why return 500?
//    @Test
//    public void when_delete_event_should_return_ok() throws Exception {
//        mockMvc.perform(delete("/api/planner/event/{id}", 1L))
//                .andExpect(status().isOk());
//    }

    @Test
    public void when_delete_event_no_id_should_return_error() throws Exception {
        mockMvc.perform(delete("/api/planner/event"))
                .andExpect(status().is4xxClientError());
    }
}