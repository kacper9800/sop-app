package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.WorkScheduleDTO;
import pl.sop.entities.WorkSchedule;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToWorkScheduleConverterTest {

    private final DTOToWorkScheduleConverter dtoToWorkScheduleConverter = new DTOToWorkScheduleConverter();

    @Test
    public void testConvert() {
        WorkScheduleDTO dto = new WorkScheduleDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        WorkSchedule workSchedule = dtoToWorkScheduleConverter.convert(dto);
        assertEquals(dto.getName(), workSchedule.getName());
        assertEquals(dto.getDescription(), workSchedule.getDescription());
    }
}