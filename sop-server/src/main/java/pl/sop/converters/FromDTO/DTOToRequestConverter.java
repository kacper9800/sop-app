package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Company;
import pl.sop.entities.Dictionary;
import pl.sop.entities.Request;
import pl.sop.entities.User;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.CollegeService;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.entities.organizationStructure.InstituteService;
import pl.sop.services.CompanyService;
import pl.sop.services.DictionaryService;
import pl.sop.services.UserService;

public class DTOToRequestConverter implements Converter<RequestDTO, Request> {


  @Override
  public Request convert(RequestDTO input) {
    Request request = new Request();
    UserService userService = new UserService();
    InstituteService instituteService = new InstituteService();
    CollegeService collegeService = new CollegeService();
    DictionaryService dictionaryService = new DictionaryService();
    CompanyService companyService = new CompanyService();

    // Base info
    request.setName(input.getName());
    request.setDescription(input.getDescription());

    Dictionary requestType = dictionaryService.getByValue(input.getRequestTypeName());
    if (requestType != null) {
      request.setRequestType(requestType);
    }

    College college = collegeService.findById(input.getCollegeId());
    if (college != null) {
      request.setCollege(college);
    }

    Institute institute = instituteService.getById(input.getInstituteId());
    if (institute != null) {
      request.setInstitute(institute);
    }

    User intern = userService.findUser(input.getInternId());
    if (intern != null) {
      request.setIntern(intern);
    }

    // Person data
    request.setPosition(input.getPosition());
    request.setPositionDescription(input.getPositionDescription());
    request.setAmountOfHours(input.getAmountOfHours());
    request.setResponsibilities(input.getResponsibilities());

    // Company data
    if (companyService.checkIfCompanyExists(input.getNip())) {
      request.setCompany(companyService.getCompanyByNip(input.getNip()));
    }

    Company company = new Company();
    company.setName(input.getCompanyName());
    company.setNip(input.getNip());
    company.setDescription(input.getDescription());
    request.setCompany(company);

    request.setInfoAgreement(input.getInfoAgreement());
    request.setProcessingAgreement(input.getProcessingAgreement());

    User moderator = userService.findUser(input.getModeratorId());
    if (request != null) {
      request.setModerator(moderator);
    }

    User admin = userService.findUser(input.getAdminId());
    if (admin != null) {
      request.setAdmin(admin);
    }

    request.setActualRequestStatus(input.getActualRequestStatus());

    request.setSendRequestDate(input.getSendRequestDate());
    request.setSendRequestStatus(input.getSendRequestStatus());
    return request;
  }
}
