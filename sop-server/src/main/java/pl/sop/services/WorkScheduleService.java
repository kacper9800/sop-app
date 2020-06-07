package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToWorkScheduleConverter;
import pl.sop.converters.ToDTO.WorkScheduleToDTOConverter;
import pl.sop.dao.entities.Event;
import pl.sop.dao.entities.User;
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

        List<Date> listOfDates = createListOfDate(workScheduleDTO.getStartDate(), workScheduleDTO.getStopDate());
        for (Date date : listOfDates) {

            for (Long eventId : workScheduleDTO.getEventsId()) {
                Event baseEvent = eventRepository.getOne(eventId);
                // Kopiowanie najważniejszych informacji
                Event newEvent = copyBaseEventDetailsToNew(baseEvent);
                for (Long userId : workScheduleDTO.getUsersId()) {
                    User user = userRepository.findUserById(userId);
                    newEvent.setUser(user);

//                    newEvent.setStartDate();
//                    newEvent.setStopDate();


                }

            }
        }

        // TODO
        // Typy proste dodajemy bez większych ceregieli
        // Listy/Kolekcje musimy mapować i robić zapis do innych tabel.
        // W jakich tabelach będzie zapis:
        // Work Schedules - ogólna definicja grafiku,
        // Events - zapis każdego eventu, dla każdej osoby
        // pętla for each (Long userId : users) {
        // User user = userService.getUser(id);
        // Event
//        return this.workScheduleRepository.save(workSchedule);
        return null;
    }


    // ToDo
    private List<Date> createListOfDate(String startDate, String endDate) {
        List<Date> dates = new ArrayList<Date>();

        Date start = convertStringDateToDate(startDate);
        Date end = convertStringDateToDate(endDate);

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

}
