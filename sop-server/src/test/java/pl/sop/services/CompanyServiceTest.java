package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.sop.dto.AddressDTO;
import pl.sop.dto.CompanyDTO;
import pl.sop.entities.Company;
import pl.sop.repositories.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    public void should_return_valid_nip() {
        boolean isValid = companyService.isNipValid("106-00-00-062");
        assertTrue(isValid);
    }

    @Test
    public void should_return_invalid_nip() {
        boolean isValid = companyService.isNipValid("10600-00-062");
        assertFalse(isValid);
    }

    @Test
    public void when_get_all_companies_should_return_company_dto_list() {
        List<Company> mockCompanies = new ArrayList<>();
        mockCompanies.add(new Company());
        mockCompanies.add(new Company());
        Mockito.when(companyRepository.getAllCompanies(Mockito.any())).thenReturn(mockCompanies);
        ResponseEntity<List<CompanyDTO>> response = companyService.getAllCompanies(null);
        assertEquals(response.getBody().size(), mockCompanies.size());
    }

    @Test
    public void when_get_company_by_id_should_return_company_dto() {
        Company mockCompany = new Company();
        mockCompany.setId(1L);
        mockCompany.setName("Company");
        Mockito.when(companyRepository.getOne(Mockito.any())).thenReturn(mockCompany);
        ResponseEntity<CompanyDTO> response = companyService.getCompanyById(mockCompany.getId());
        assertSame(response.getBody().getName(), mockCompany.getName());
    }

    @Test
    public void when_get_company_by_nip_should_return_company() {
        Company mockCompany = new Company();
        mockCompany.setId(1L);
        mockCompany.setName("Company");
        Mockito.when(companyRepository.getCompanyByNip(Mockito.any())).thenReturn(mockCompany);
        Company company = companyService.getCompanyByNip(mockCompany.getNip());
        assertSame(company.getName(), mockCompany.getName());
    }

    @Test
    public void when_create_company_dto_it_should_return_company() {
        CompanyDTO dto = new CompanyDTO("Company", new AddressDTO("City", "Street", "1", "00-111"));
        Company company = new Company();
        company.setName("Company");
        Mockito.when(companyRepository.save(Mockito.any(Company.class))).thenReturn(company);
        ResponseEntity<Company> response = companyService.createCompany(dto);
        assertEquals(response.getBody().getName(), dto.getName());
    }

    @Test
    public void when_delete_company_dto_it_should_return_string() {
        ResponseEntity<String> response = companyService.deleteCompany(0L);
        assertEquals(response.getBody(), "Company deleted!");
    }

    @Test
    public void should_return_company_exists() {
        List<Company> mockCompanies = new ArrayList<>();
        mockCompanies.add(new Company());
        Mockito.when(companyRepository.checkIfCompanyExists(Mockito.any())).thenReturn(mockCompanies);
        boolean isExists = companyService.checkIfCompanyExists("");
        assertTrue(isExists);
    }

    @Test
    public void should_return_company_not_exists() {
        List<Company> mockCompanies = new ArrayList<>();
        Mockito.when(companyRepository.checkIfCompanyExists(Mockito.any())).thenReturn(mockCompanies);
        boolean isExists = companyService.checkIfCompanyExists("");
        assertFalse(isExists);
    }
}