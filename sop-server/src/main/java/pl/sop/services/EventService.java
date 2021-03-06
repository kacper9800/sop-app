package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToEventConverter;
import pl.sop.converters.ToDTO.EventToDTOConverter;
import pl.sop.entities.Event;
import pl.sop.repositories.EventRepository;
import pl.sop.dto.EventDTO;

import java.text.ParseException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    private final EventToDTOConverter eventToDTOConverter = new EventToDTOConverter();
    private final DTOToEventConverter dtoToEventConverter = new DTOToEventConverter();

    public EventService() {
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAllEvents();
        List<EventDTO> eventDTOS = events.stream().map(item -> eventToDTOConverter.convert(item)).collect(toList());
        return eventDTOS;
    }

    public List<EventDTO> getAllEventsForUserId(Long id) {
        List<Event> events = eventRepository.findAllEventsForUserId(id);
        List<EventDTO> eventDTOS = events.stream().map(event -> eventToDTOConverter.convert(event)).collect(toList());
        return eventDTOS;
    }

    public List<EventDTO> getAllEventsWithoutDate() {
        List<Event> events = eventRepository.findAllEventWithoutDate();
        List<EventDTO> eventDTOS = events.stream().map(event -> eventToDTOConverter.convert(event)).collect(toList());
        return eventDTOS;
    }

    public Event createEvent(EventDTO eventDTO) throws ParseException {
        Event event = dtoToEventConverter.convert(eventDTO);
        return this.eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }
}
