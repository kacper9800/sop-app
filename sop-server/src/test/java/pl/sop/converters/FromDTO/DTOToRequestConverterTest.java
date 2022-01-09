package pl.sop.converters.FromDTO;

import org.junit.Test;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;

import static org.junit.Assert.*;

public class DTOToRequestConverterTest {

    private final DTOToRequestConverter dtoToRequestConverter = new DTOToRequestConverter();

    @Test
    public void testConvert() {
        RequestDTO dto = new RequestDTO();
        dto.setName("Name");
        dto.setDescription("Description");
        Request request = dtoToRequestConverter.convert(dto);
        assertEquals(dto.getDescription(), request.getDescription());
        assertEquals(dto.getName(), request.getName());
    }
}