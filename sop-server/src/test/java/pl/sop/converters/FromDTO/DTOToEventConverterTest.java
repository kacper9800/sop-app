package pl.sop.converters.FromDTO;

import org.junit.Test;
import pl.sop.dto.EventDTO;
import pl.sop.entities.Event;

import static org.junit.Assert.*;

public class DTOToEventConverterTest {

    private final DTOToEventConverter dtoToEventConverter = new DTOToEventConverter();

    @Test
    public void testConvert() {
        EventDTO dto = new EventDTO();
        dto.setName("Event");
        dto.setDescription("Description");
        Event event = dtoToEventConverter.convert(dto);
        assertEquals(dto.getName(), event.getName());
        assertEquals(dto.getDescription(), event.getDescription());
    }
}