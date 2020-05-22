package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dto.WorkScheduleDTO;

public class DTOToWorkScheduleConverter implements Converter<WorkScheduleDTO, WorkSchedule> {

    @Override
    public WorkSchedule convert(WorkScheduleDTO input) {
        WorkSchedule workSchedule = new WorkSchedule();
        return workSchedule;
    }
}
