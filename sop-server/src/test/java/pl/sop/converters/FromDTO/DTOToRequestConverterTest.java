package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;
import pl.sop.enums.ERequestStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToRequestConverterTest {

    private final DTOToRequestConverter dtoToRequestConverter = new DTOToRequestConverter();

    @Test
    public void testConvert() {
        RequestDTO dto = new RequestDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        dto.setPosition("Position");
        dto.setPositionDescription("Position description");
        dto.setAmountOfHours(20);
        dto.setResponsibilities("Responsibilities");
        dto.setPracticeSuperviserName("Name");
        dto.setPracticeSuperviserLastName("Lastname");
        dto.setPracticeSuperviserEmail("Email");
        dto.setPracticeSuperviserPhone("012345");
        Request request = dtoToRequestConverter.convert(dto);
        assertEquals(dto.getDescription(), request.getDescription());
        assertEquals(dto.getName(), request.getName());
        assertEquals(dto.getPosition(), request.getPosition());
        assertEquals(dto.getPositionDescription(), request.getPositionDescription());
        assertEquals(dto.getAmountOfHours(), request.getAmountOfHours());
        assertEquals(dto.getResponsibilities(), request.getResponsibilities());
        assertEquals(dto.getPracticeSuperviserName(), request.getPracticeSuperviserName());
        assertEquals(dto.getPracticeSuperviserLastName(), request.getPracticeSuperviserLastName());
        assertEquals(dto.getPracticeSuperviserEmail(), request.getPracticeSuperviseEmail());
        assertEquals(dto.getPracticeSuperviserPhone(), request.getPracticeSuperviserPhone());
        assertEquals(ERequestStatus.SENT, request.getActualRequestStatus());
        assertEquals(ERequestStatus.SENT, request.getSendRequestStatus());
    }
}