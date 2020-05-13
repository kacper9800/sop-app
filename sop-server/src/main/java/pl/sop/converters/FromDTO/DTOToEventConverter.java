package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Event;
import pl.sop.dao.entities.User;
import pl.sop.dto.EventDTO;

public class DTOToEventConverter implements Converter<EventDTO, Event> {
    @Override
    public Event convert(EventDTO input) {
        Event event = new Event();
        if (input.getId() != null) {
            event.setId(input.getId());
        }
        event.setName(input.getName());
        event.setDescription(input.getDescription());
        event.setStartDate(input.getStartDate());
        event.setStopDate(input.getStopDate());
        if (input.getUserId() != null) {
            User user = new User();
            user.setId(input.getUserId());
            event.setUser(user);
        }
        return event;
    }
}
