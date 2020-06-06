package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToWorkScheduleConverter;
import pl.sop.converters.ToDTO.WorkScheduleToDTOConverter;
import pl.sop.dao.entities.User;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dao.repository.UserRepository;
import pl.sop.dao.repository.WorkScheduleRepository;
import pl.sop.dto.WorkScheduleDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class WorkScheduleService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    private final WorkScheduleToDTOConverter workScheduleToDTOConverter = new WorkScheduleToDTOConverter();
    private final DTOToWorkScheduleConverter dtoToWorkScheduleConverter = new DTOToWorkScheduleConverter();

    public WorkScheduleService() {
    }

    public List<WorkScheduleDTO> getAllWorkSchedules() {
        List<WorkSchedule> workSchedules = workScheduleRepository.findAllWorkSchedules();
        List<WorkScheduleDTO> workScheduleDTOS = workSchedules.stream().map(item -> workScheduleToDTOConverter.convert(item)).collect(toList());
        return workScheduleDTOS;
    }

    public WorkSchedule createWorkSchedule(WorkScheduleDTO workScheduleDTO) {
        for (Long userId : workScheduleDTO.getUsersId()) {
            User user = userRepository.findUserById(userId);


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

    public void deleteWorkSchedule(Long id) {
        this.workScheduleRepository.deleteById(id);
    }
}
