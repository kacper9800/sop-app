package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Event;
import pl.sop.dto.EventDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventToDTOConverter implements Converter<Event, EventDTO> {

    @Override
    public EventDTO convert(Event input) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(input.getId());
        eventDTO.setName(input.getName());
        eventDTO.setDescription(input.getDescription());
        eventDTO.setStartDate(convertDateToStringDate(input.getStartDate()));
        eventDTO.setStopDate(convertDateToStringDate(input.getStopDate()));
//        eventDTO.setAllDay(input.getAllDay());
        eventDTO.setUserId(input.getUser().getId());
        return eventDTO;
    }

    public String convertDateToStringDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return df.format(date);
    }
}
