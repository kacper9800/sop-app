package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.AddressDTO;
import pl.sop.entities.Address;

public class DTOToAddressConverter implements Converter<AddressDTO, Address> {

  @Override
  public Address convert(AddressDTO input) {
    Address address = new Address();
//    address.setCompany();

    address.setCity(input.getCity());
    address.setStreet(input.getStreet());
    address.setNumber(input.getNumber());
    address.setPostCode(input.getPostCode());
    return address;
  }
}
