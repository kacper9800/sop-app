package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;

public class CompanyToDTOConverter implements Converter<Company, CompanyDTO> {

  private final AddressToDTOConverter addressToDTOConverter = new AddressToDTOConverter();

  @Override
  public CompanyDTO convert(Company input) {
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setName(input.getName());
    companyDTO.setAddressDTO(addressToDTOConverter.convert(input.getAddress()));
    return companyDTO;
  }
}
