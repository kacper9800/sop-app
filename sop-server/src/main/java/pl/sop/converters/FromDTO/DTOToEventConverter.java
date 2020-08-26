package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.entities.Event;
import pl.sop.entities.Location;
import pl.sop.entities.User;
import pl.sop.dto.EventDTO;
import pl.sop.enums.EventDuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DTOToEventConverter implements Converter<EventDTO, Event> {
    @Override
    public Event convert(EventDTO input) {
        Event event = new Event();
        if (input.getId() != null) {
            event.setId(input.getId());
        }
        event.setName(input.getName());
        event.setDescription(input.getDescription());
        if (input.getDuration() != null) {
            if (input.getDuration().equals(EventDuration.QUARTER1.getType())) {
                event.setDuration(convertDuration("00:15"));
            } else if (input.getDuration().equals(EventDuration.HALFHOUR.getType())) {
                event.setDuration(convertDuration("00:30"));
            } else if (input.getDuration().equals(EventDuration.QUARTER3.getType())) {
                event.setDuration(convertDuration("00:45"));
            } else if (input.getDuration().equals(EventDuration.ONEHOUR.getType())) {
                event.setDuration(convertDuration("01:00"));
            } else if (input.getDuration().equals(EventDuration.ONEHOURANDHALF.getType())) {
                event.setDuration(convertDuration("01:30"));
            } else if (input.getDuration().equals(EventDuration.TWOHOURSANDQUARTER.getType())) {
                event.setDuration(convertDuration("02:15"));
            }
        }
        //        event.setStartDate(convertStringDateToDate(input.getStartDate()));
        //        event.setStopDate(convertStringDateToDate(input.getStopDate()));

        if (input.getInstructorId() != null) {
            User instructor = new User();
            instructor.setId(input.getInstructorId());
            event.setInstructor(instructor);
        }

        if (input.getLocationId() != null) {
            Location location = new Location();
            location.setId(input.getLocationId());
            event.setLocation(location);
        } else {
            event.setLocation(null);
        }
//        if (input.getInstructorId() != null) {
//            User user = new User();
//            user.setId(input.getInstructorId());
//            event.setUser(user);
//        } else {
//            event.setUser(null);
//        }
//
//        if (input.getFacultyId() != null) {
//            Faculty faculty = new Faculty();
//            faculty.setId(input.getFacultyId());
//            event.setFaculty(faculty);
//        } else {
//            event.setFaculty(null);
//        }
//
//        if (input.getCollegeId() != null) {
//            College college = new College();
//            college.setId(input.getCollegeId());
//            event.setCollege(college);
//        } else {
//            event.setCollege(null);
//        }
//
//        if (input.getDepartmentId() != null) {
//            Department department = new Department();
//            department.setId(input.getDepartmentId());
//            event.setDepartment(department);
//        }
//
//        if (input.getUserId() != null) {
//            User user = new User();
//            user.setId(input.getUserId());
//            event.setUser(user);
//        } else {
//            event.setUser(null);
//        }
        event.setActive(true);
        event.setDeleted(false);
        return event;
    }

    private String convertDuration(String duration) {
        String[] values = duration.split(":");

        Duration durationObject = Duration.ofHours(Integer.parseInt(values[0]));
        durationObject = durationObject.plusMinutes(Integer.parseInt(values[1]));
        return durationObject.toString();
    }

    private Date convertStringDateToDate(String dateToConvert) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        if (dateToConvert != null) {
            date = simpleDateFormat.parse(dateToConvert);
        } else {
            date = null;
        }
        return date;
    }
}
