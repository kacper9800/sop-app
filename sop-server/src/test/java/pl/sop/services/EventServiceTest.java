package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sop.dto.EventDTO;
import pl.sop.entities.Event;
import pl.sop.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventService eventService;

    @Test
    public void when_get_all_events_should_return_event_dto_list() {
        List<Event> mockEvents = new ArrayList<>();
        mockEvents.add(new Event());
        mockEvents.add(new Event());
        Mockito.when(eventRepository.findAllEvents()).thenReturn(mockEvents);
        List<EventDTO> response = eventService.getAllEvents();
        assertEquals(response.size(), mockEvents.size());
    }

    @Test
    public void when_get_all_events_for_user_id_should_return_event_dto_list() {
        List<Event> mockEvents = new ArrayList<>();
        mockEvents.add(new Event());
        mockEvents.add(new Event());
        Mockito.when(eventRepository.findAllEventsForUserId(Mockito.any())).thenReturn(mockEvents);
        List<EventDTO> response = eventService.getAllEventsForUserId(0L);
        assertEquals(response.size(), mockEvents.size());
    }

    @Test
    public void when_get_all_events_without_date_should_return_event_dto_list() {
        List<Event> mockEvents = new ArrayList<>();
        mockEvents.add(new Event());
        mockEvents.add(new Event());
        Mockito.when(eventRepository.findAllEventWithoutDate()).thenReturn(mockEvents);
        List<EventDTO> response = eventService.getAllEventsWithoutDate();
        assertEquals(response.size(), mockEvents.size());
    }

    //TODO: ClassCastException?
//    @Test
//    public void when_create_event_dto_should_return_event() throws ParseException {
//        EventDTO mockEvent = new EventDTO();
//        mockEvent.setName("Event");
//        Mockito.when(eventRepository.save(Mockito.any())).thenReturn(mockEvent);
//        Event response = eventService.createEvent(mockEvent);
//        assertEquals(response.getName(), mockEvent.getName());
//    }

    @Test
    public void when_delete_event_return_nothing() {
        eventService.deleteEvent(0L);
    }
}