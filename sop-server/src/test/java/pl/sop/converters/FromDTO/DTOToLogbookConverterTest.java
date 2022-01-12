package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.LogbookDTO;
import pl.sop.entities.Logbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToLogbookConverterTest {

    private final DTOToLogbookConverter dtoToLogbookConverter = new DTOToLogbookConverter();

    @Test
    public void testConvert() {
        LogbookDTO dto = new LogbookDTO();
        dto.setName("Logbook");
        dto.setDescription("Description");
        Logbook logbook = dtoToLogbookConverter.convert(dto);
        assertEquals(dto.getName(), logbook.getName());
        assertEquals(dto.getDescription(), logbook.getDescription());
    }
}