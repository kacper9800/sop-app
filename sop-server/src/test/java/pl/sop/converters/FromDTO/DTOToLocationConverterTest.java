package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.LocationDTO;
import pl.sop.entities.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToLocationConverterTest {

    private final DTOToLocationConverter dtoToLocationConverter = new DTOToLocationConverter();

    @Test
    public void testConvert() {
        LocationDTO dto = new LocationDTO();
        dto.setId(1L);
        dto.setName("Location");
        dto.setAddress("Address");
        dto.setFloor(3);
        dto.setRoom("10");
        Location location = dtoToLocationConverter.convert(dto);
        assertEquals(dto.getId(), location.getId());
        assertEquals(dto.getName(), location.getName());
        assertEquals(dto.getAddress(), location.getAddress());
        assertEquals(dto.getFloor(), location.getFloor());
        assertEquals(dto.getRoom(), location.getRoom());
    }
}