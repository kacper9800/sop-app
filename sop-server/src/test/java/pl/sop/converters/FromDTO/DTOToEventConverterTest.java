package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.EventDTO;
import pl.sop.entities.Event;
import pl.sop.enums.EventDuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToEventConverterTest {

    private final DTOToEventConverter dtoToEventConverter = new DTOToEventConverter();

    @Test
    public void testConvert() {
        EventDTO dto = new EventDTO();
        dto.setId(1L);
        dto.setName("Event");
        dto.setDescription("Description");
        dto.setDuration(EventDuration.HALFHOUR.getType());
        Event event = dtoToEventConverter.convert(dto);
        assertEquals(dto.getId(), event.getId());
        assertEquals(dto.getName(), event.getName());
        assertEquals(dto.getDescription(), event.getDescription());
        assertEquals("PT30M", event.getDuration());
    }
}