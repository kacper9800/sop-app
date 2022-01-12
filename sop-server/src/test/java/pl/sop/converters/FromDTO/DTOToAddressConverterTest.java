package pl.sop.converters.FromDTO;

import org.junit.jupiter.api.Test;
import pl.sop.dto.AddressDTO;
import pl.sop.entities.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToAddressConverterTest {

    private final DTOToAddressConverter dtoToAddressConverter = new DTOToAddressConverter();

    @Test
    public void testConvert() {
        AddressDTO dto = new AddressDTO("Kraków", "Podchorążych", "2", "30-084");
        Address address = dtoToAddressConverter.convert(dto);
        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getStreet(), address.getStreet());
        assertEquals(dto.getNumber(), address.getNumber());
        assertEquals(dto.getPostCode(), address.getPostCode());
    }
}