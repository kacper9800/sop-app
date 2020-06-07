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
        if (input.getId() != null) {
            eventDTO.setId(input.getId());
        }
        eventDTO.setName(input.getName());
        if (input.getDescription() != null) {
            eventDTO.setDescription(input.getDescription());
        } else {
            eventDTO.setDescription(null);
        }

        if (input.getDuration() != null) {
            eventDTO.setDuration(convertDuration(input.getDuration()));
        } else {
            eventDTO.setDuration(null);
        }
        if (input.getInstructor() != null) {
            if (input.getInstructor().getFirstName() != null) {
                eventDTO.setInstructorName(input.getInstructor().getFirstName());
            } else {
                eventDTO.setInstructorName(null);
            }
            if (input.getInstructor().getLastName() != null) {
                eventDTO.setInstructorName(eventDTO.getInstructorName() + " " + input.getInstructor().getLastName());
            } else {
                eventDTO.setInstructorName(null);
            }
        } else {
            eventDTO.setInstructorName(null);
        }
        if (input.getActive() != null) {
            eventDTO.setActive(input.getActive());
        } else {
            eventDTO.setActive(false);
        }

        if (input.getLocation() != null) {
            eventDTO.setLocationId(input.getLocation().getId());
        }
//        eventDTO.setStartDate(convertDateToStringDate(input.getStartDate()));
//        eventDTO.setStopDate(convertDateToStringDate(input.getStopDate()));
//        eventDTO.setAllDay(input.getAllDay());
//        eventDTO.setUserId(input.getUser().getId());
        return eventDTO;
    }

    public Integer convertDuration(String duration) {
        switch (duration) {
            case "PT15M":
                return 0;
            case "PT30M":
                return 1;
            case "PT45M":
                return 2;
            case "PT1H":
                return 3;
            case "PT1H30M":
                return 4;
            case "PT2H15M":
                return 5;
        }
        return null;
    }

    public String convertDateToStringDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return df.format(date);
    }
}
