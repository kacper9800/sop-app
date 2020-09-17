package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.AddressDTO;
import pl.sop.entities.Address;

public class AddressToDTOConverter implements Converter<Address, AddressDTO> {

  @Override
  public AddressDTO convert(Address input) {
    AddressDTO addressDTO = new AddressDTO();
    addressDTO.setCity(input.getCity());
    addressDTO.setStreet(input.getStreet());
    addressDTO.setNumber(input.getNumber());
    addressDTO.setPostCode(input.getPostCode());
    return addressDTO;
  }
}
