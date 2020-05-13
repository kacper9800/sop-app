package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Event;
import pl.sop.dto.EventDTO;

public class EventToDTOConverter implements Converter<Event, EventDTO> {

    @Override
    public EventDTO convert(Event input) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(input.getId());
        eventDTO.setName(input.getName());
        eventDTO.setDescription(input.getDescription());
        eventDTO.setStartDate(input.getStartDate());
        eventDTO.setStopDate(input.getStopDate());
//        eventDTO.setAllDay(input.getAllDay());
        eventDTO.setUserId(input.getUser().getId());
        return eventDTO;
    }
}
