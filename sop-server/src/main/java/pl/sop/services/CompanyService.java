package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToCompanyConverter;
import pl.sop.converters.ToDTO.CompanyToDTOConverter;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;
import pl.sop.repositories.CompanyRepository;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  private final CompanyToDTOConverter companyToDTOConverter = new CompanyToDTOConverter();
  private final DTOToCompanyConverter dtoToCompanyConverter = new DTOToCompanyConverter();

  public ResponseEntity getAllCompanies(Long id) {
    List<Company> companies = companyRepository.getAllCompanies(id);
    List<CompanyDTO> companyDTOS = companies.stream().map(companyToDTOConverter::convert).collect(Collectors.toList());
    return new ResponseEntity(companyDTOS, HttpStatus.OK);
  }

  public ResponseEntity getCompanyById(Long id) {
    if (id == null) {
      return new ResponseEntity("Bad request! Id is null!", HttpStatus.NOT_FOUND);
    }
    Company company = companyRepository.getOne(id);
    CompanyDTO companyDTO = companyToDTOConverter.convert(company);
    return new ResponseEntity(companyDTO, HttpStatus.OK);
  }

  public ResponseEntity createCompany(CompanyDTO companyDTO) {
    if (companyDTO.getName() == null) {
      return new ResponseEntity("Bad request!", HttpStatus.NOT_ACCEPTABLE);
    }
    Company company = dtoToCompanyConverter.convert(companyDTO);
    return ResponseEntity.ok(companyRepository.save(company));
  }

  public ResponseEntity deleteCompany(Long id) {
    if (id == null) {
      return new ResponseEntity("Bad reqeust! Id is null!", HttpStatus.NOT_FOUND);
    }
    companyRepository.deleteById(id);
    return ResponseEntity.ok("Company deleted!");
  }
}
