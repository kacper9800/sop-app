package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.WorkSchedule;
import pl.sop.dto.WorkScheduleDTO;

public class DTOToWorkScheduleConverter implements Converter<WorkScheduleDTO, WorkSchedule> {

    @Override
    public WorkSchedule convert(WorkScheduleDTO input) {
        WorkSchedule workSchedule = new WorkSchedule();
        if (input.getName() != null) {
            workSchedule.setName(input.getName());
        }
        if (input.getDescription() != null) {
            workSchedule.setDescription(input.getDescription());
        }
//        if(input.getStartDate() != null) {
//            workSchedule.setStartDate(input.getStartDate());
//        }
//        if(input.getStopDate() != null) {
//            workSchedule.setStopDate(input.getStopDate());
//        }
//        if (input.getBreaks() != null) {
//            workSchedule.setBreaks_duration(input.getBreaks());
//        }
//        if (input.getStartHour() != null) {
//            workSchedule.setStartHour(input.getStartHour());
//        }
//        if (input.getStopHour() != null) {
//            workSchedule.setStopHour(input.getStopHour());
//        }

        return workSchedule;
    }
}
