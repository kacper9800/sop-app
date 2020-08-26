package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToWorkScheduleConverter;
import pl.sop.converters.ToDTO.WorkScheduleToDTOConverter;
import pl.sop.entities.Event;
import pl.sop.entities.WorkSchedule;
import pl.sop.repositories.EventRepository;
import pl.sop.repositories.UserRepository;
import pl.sop.repositories.WorkScheduleRepository;
import pl.sop.dto.WorkScheduleDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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


    public List<Event> createWorkSchedule(WorkScheduleDTO workScheduleDTO) {
        WorkSchedule workSchedule = dtoToWorkScheduleConverter.convert(workScheduleDTO);

        List<Date> listOfDates = createListOfDate(workScheduleDTO.getStartDate(), workScheduleDTO.getStopDate(), workScheduleDTO.getStartHour());
        List<Event> listOfEvent = new ArrayList<Event>();
        Date currentTime = listOfDates.get(0); //pobranie pierwszego dnia
        Integer index = 0;
        aa:
        for (Date date : listOfDates) {
            for (Long eventId : workScheduleDTO.getEventsId()) {
                Date maxDate = createActualDateWithTime(workScheduleDTO.getStopHour(), (Date) date.clone());
                if (currentTime.getTime() < maxDate.getTime()) {

                    Event baseEvent = eventRepository.getOne(eventId);
                    // Kopiowanie najważniejszych informacji
                    Event newEvent = new Event();
                    newEvent = copyBaseEventDetailsToNew(baseEvent);


                    // Ustawienie czasu

                    setTimeOfEvent(newEvent, currentTime);
                    Long newTime = currentTime.getTime() + convertEventDuration(newEvent.getDuration()) + convertTimeOfBreak(workScheduleDTO.getBreaks());
                    Date newCurrentTime = convertMsDateToDate(newTime);
                    currentTime = newCurrentTime;

                    listOfEvent.add(newEvent);
                } else {
                    if (index + 1 < listOfDates.size()) {
                        index = index + 1;
                        currentTime = listOfDates.get(index);
                        continue aa;
                    }
                }

            }
        }

        // Zapis wszystkich eventów dla wsakazanych userów
        for (Event event : listOfEvent) {
            for (Long userId : workScheduleDTO.getUsersId()) {
                event.setUser(userRepository.findUserById(userId));
            }
        }

        return eventRepository.saveAll(listOfEvent);
//        return workScheduleRepository.save(workSchedule);
    }

    private Date createActualDateWithTime(String stopHour, Date actualDate) {
        String[] hours = splitStringHour(stopHour);

        actualDate.setHours(Integer.parseInt(hours[0]));
        actualDate.setMinutes(Integer.parseInt(hours[1]));

        return actualDate;
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


    private Event setTimeOfEvent(Event newEvent, Date currentTime) {
        newEvent.setStartDate(currentTime);
        newEvent.setStopDate(convertMsDateToDate(calculateEndOfEvent(currentTime, newEvent.getDuration())));

        return newEvent;
    }

    private Date convertMsDateToDate(Long date) {
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date result = new Date(date);
        return result;
    }

    private Long calculateEndOfEvent(Date currentTime, String duration) {
        long time = currentTime.getTime();
        return time + convertEventDuration(duration);
    }

    //Converter for events duration
    public long convertEventDuration(String duration) {
        HashMap<String, Long> durations = new HashMap<>();
        durations.put("PT15M", 900000L);
        durations.put("PT30M", 1800000L);
        durations.put("PT45M", 2700000L);
        durations.put("PT1H", 3600000L);
        durations.put("PT1H30M", 5400000L);
        durations.put("PT2H15M", 8100000L);
        return durations.get(duration);
    }

    public long convertTimeOfBreak(Integer duration) {
        HashMap<Integer, Long> durations = new HashMap<>();
        durations.put(0, 600000L);
        durations.put(1, 900000L);
        durations.put(2, 1200000L);
        durations.put(3, 1800000L);
        durations.put(4, 2700000L);
        durations.put(5, 3600000L);
        return durations.get(duration);
    }
}
