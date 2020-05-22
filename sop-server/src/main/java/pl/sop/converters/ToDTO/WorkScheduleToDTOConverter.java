package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dto.WorkScheduleDTO;

public class WorkScheduleToDTOConverter implements Converter<WorkSchedule, WorkScheduleDTO> {

    @Override
    public WorkScheduleDTO convert(WorkSchedule input) {
        WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();
        return workScheduleDTO;
    }
}
