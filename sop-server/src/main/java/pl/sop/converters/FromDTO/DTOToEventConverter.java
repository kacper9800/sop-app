package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Event;
import pl.sop.dao.entities.User;
import pl.sop.dto.EventDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DTOToEventConverter implements Converter<EventDTO, Event> {
    @Override
    public Event convert(EventDTO input) throws ParseException {
        Event event = new Event();
        if (input.getId() != null) {
            event.setId(input.getId());
        }
        event.setName(input.getName());
        event.setDescription(input.getDescription());
        event.setStartDate(convertStringDateToDate(input.getStartDate()));
        event.setStopDate(convertStringDateToDate(input.getStopDate()));
        if (input.getUserId() != null) {
            User user = new User();
            user.setId(input.getUserId());
            event.setUser(user);
        }
        return event;
    }

    private Date convertStringDateToDate(String dateToConvert) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        if (dateToConvert != null) {
            date = simpleDateFormat.parse(dateToConvert);
        } else {
            date = null;
        }
        return date;
    }
}
