package pl.sop.services;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToWorkScheduleConverter;
import pl.sop.converters.ToDTO.WorkScheduleToDTOConverter;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dao.repository.WorkScheduleRepository;
import pl.sop.dto.WorkScheduleDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class WorkScheduleService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

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
        WorkSchedule workSchedule = dtoToWorkScheduleConverter.convert(workScheduleDTO);
        return this.workScheduleRepository.save(workSchedule);
    }

    public void deleteWorkSchedule(Long id) {
        this.workScheduleRepository.deleteById(id);
    }
}
