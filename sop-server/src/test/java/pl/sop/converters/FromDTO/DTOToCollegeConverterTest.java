package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.AddressDTO;
import pl.sop.dto.CollegeDTO;
import pl.sop.entities.Address;
import pl.sop.entities.organizationStructure.College;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DTOToCollegeConverterTest {

    private final DTOToCollegeConverter dtoToCollegeConverter = new DTOToCollegeConverter();

    @Test
    public void testConvert() {
        CollegeDTO dto = new CollegeDTO(1L, "College", true, false);
        College college = dtoToCollegeConverter.convert(dto);
        assertEquals(dto.getId(), college.getId());
        assertEquals(dto.getName(), college.getName());
        assertEquals(dto.getActive(), college.getActive());
        assertEquals(dto.getDeleted(), college.getDeleted());
    }
}