package pl.sop.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.sop.dto.AddressDTO;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;
import pl.sop.repositories.CompanyRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void when_save_company_dto_it_should_return_company() {
        CompanyDTO dto = new CompanyDTO("Company", new AddressDTO("City", "Street", "1", "00-111"));
        Company company = new Company();
        company.setName("Company");
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenReturn(company);
        ResponseEntity<Company> response = companyService.createCompany(dto);
        assertTrue(response.getBody().getName().equals(dto.getName()));
    }
}