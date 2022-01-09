package pl.sop.converters.FromDTO;

import org.junit.Test;
import pl.sop.dto.LogbookPostDTO;
import pl.sop.entities.LogbookPost;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DTOToLogbookPostConverterTest {

    private final DTOToLogbookPostConverter dtoToLogbookPostConverter = new DTOToLogbookPostConverter();

    @Test
    public void testConvert() {
        LogbookPostDTO dto = new LogbookPostDTO();
        dto.setDescription("Description");
        dto.setDate(LocalDate.now());
        dto.setAmountOfHours(40);
        LogbookPost logbookPost = dtoToLogbookPostConverter.convert(dto);
        assertEquals(dto.getDescription(), logbookPost.getDescription());
        assertEquals(dto.getDate(), logbookPost.getDate());
        assertEquals(dto.getAmountOfHours(), logbookPost.getHours());
    }
}