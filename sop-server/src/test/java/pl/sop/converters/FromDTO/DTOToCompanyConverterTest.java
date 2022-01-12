package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.AddressDTO;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToCompanyConverterTest {

    private final DTOToCompanyConverter dtoToCompanyConverter = new DTOToCompanyConverter();

    @Test
    public void testConvert() {
        AddressDTO addressDTO = new AddressDTO("Kraków", "Podchorążych", "2", "30-084");
        CompanyDTO dto = new CompanyDTO("UP", addressDTO);
        Company company = dtoToCompanyConverter.convert(dto);
        assertEquals(dto.getName(), company.getName());
        assertEquals(addressDTO.getCity(), company.getAddress().getCity());
        assertEquals(addressDTO.getStreet(), company.getAddress().getStreet());
        assertEquals(addressDTO.getNumber(), company.getAddress().getNumber());
        assertEquals(addressDTO.getPostCode(), company.getAddress().getPostCode());
    }
}
