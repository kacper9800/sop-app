package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.converters.ToDTO.AddressToDTOConverter;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;

public class DTOToCompanyConverter implements Converter<CompanyDTO, Company> {

  private final DTOToAddressConverter dtoToAddressConverter = new DTOToAddressConverter();

  @Override
  public Company convert(CompanyDTO input) {
    Company company = new Company();
    company.setName(input.getName());
    company.setAddress(dtoToAddressConverter.convert(input.getAddressDTO()));
    return company;
  }
}
