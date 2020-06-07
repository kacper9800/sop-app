package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToWorkScheduleConverter;
import pl.sop.converters.ToDTO.WorkScheduleToDTOConverter;
import pl.sop.dao.entities.Event;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dao.repository.EventRepository;
import pl.sop.dao.repository.UserRepository;
import pl.sop.dao.repository.WorkScheduleRepository;
import pl.sop.dto.WorkScheduleDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class WorkScheduleService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    private final WorkScheduleToDTOConverter workScheduleToDTOConverter = new WorkScheduleToDTOConverter();
    private final DTOToWorkScheduleConverter dtoToWorkScheduleConverter = new DTOToWorkScheduleConverter();

    public WorkScheduleService() {
    }

    public List<WorkScheduleDTO> getAllWorkSchedules() {
        List<WorkSchedule> workSchedules = workScheduleRepository.findAllWorkSchedules();
        List<WorkScheduleDTO> workScheduleDTOS = workSchedules.stream().map(item -> workScheduleToDTOConverter.convert(item)).collect(toList());
        return workScheduleDTOS;
    }

    public void deleteWorkSchedule(Long id) {
        this.workScheduleRepository.deleteById(id);
    }


    public WorkSchedule createWorkSchedule(WorkScheduleDTO workScheduleDTO) {
        WorkSchedule workSchedule = dtoToWorkScheduleConverter.convert(workScheduleDTO);

        List<Date> listOfDates = createListOfDate(workScheduleDTO.getStartDate(), workScheduleDTO.getStopDate(), workScheduleDTO.getStartHour());
        List<Event> listOfEvent = new ArrayList<Event>();
        Date currentTime = listOfDates.get(0); //pobranie pierwszego dnia

        for (Date date : listOfDates) {

            if (currentTime.equals(date)) {

                for (Long eventId : workScheduleDTO.getEventsId()) {
                    Event baseEvent = eventRepository.getOne(eventId);
                    // Kopiowanie najważniejszych informacji
                    Event newEvent = copyBaseEventDetailsToNew(baseEvent);


                    // Ustawienie czasu

                    setTimeOfEvent(newEvent, currentTime, workScheduleDTO.getStopHour(), workScheduleDTO.getBreaks());

                    listOfEvent.add(newEvent);
                }
            } else {
                break;
            }
        }

        // Zapis wszystkich eventów dla wsakazanych userów
        for (Event event : listOfEvent) {
            for (Long userId : workScheduleDTO.getUsersId()) {
                event.setUser(userRepository.findUserById(userId));
//                eventRepository.save(event);
            }
        }

//        workScheduleRepository.save(workSchedule);
        return null;
    }


    private List<Date> createListOfDate(String startDate, String endDate, String startTime) {
        List<Date> dates = new ArrayList<Date>();

        Date start = convertStringDateToDate(startDate);
        Date end = convertStringDateToDate(endDate);

        String[] hours = splitStringHour(startTime);
        start.setHours(Integer.parseInt(hours[0]));
        start.setMinutes(Integer.parseInt(hours[1]));

        long interval = 24 * 1000 * 60 * 60; // 1 day in millis
        long actualTime = start.getTime();
        long endTime = end.getTime();
        while (actualTime < endTime) {
            Date newDate = new Date(actualTime);
            if (newDate.getDay() == 0 || newDate.getDay() == 6) {
                actualTime += interval;
            } else {
                dates.add(newDate);
                actualTime += interval;
            }
        }
        return dates;
    }

    private String[] splitStringHour(String hour) {
        return hour.split(":");
    }


    private Event copyBaseEventDetailsToNew(Event baseEvent) {
        Event newEvent = new Event();
        newEvent.setName(baseEvent.getName());
        newEvent.setDescription(baseEvent.getDescription());
        newEvent.setDuration(baseEvent.getDuration());
        newEvent.setLocation(baseEvent.getLocation());
        newEvent.setInstructor(baseEvent.getInstructor());
        newEvent.setDeleted(baseEvent.getDeleted());
        newEvent.setActive(baseEvent.getActive());
        return newEvent;
    }

    public Date convertStringDateToDate(String date) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date newDate = formatter.parse(date);
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Converter for events duration
    public Date convertEventDuration(String duration) {

        return new Date();
    }

    private Event setTimeOfEvent(Event newEvent, Date currentTime, String maxHour, Integer timeOfBreak) {
        String[] endHour = splitStringHour(maxHour);
        newEvent.setStartDate(currentTime);
//        calculateEventDuration()
//        newEvent.setStopDate(currentTime + convertEventDuration(newEvent.getDuration());

        if (currentTime.getHours() > Integer.parseInt(endHour[0])) {

        }
        return newEvent;
    }

}
