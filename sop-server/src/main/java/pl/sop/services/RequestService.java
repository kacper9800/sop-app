package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToRequestConverter;
import pl.sop.converters.ToDTO.RequestToDTOConverter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Company;
import pl.sop.entities.Dictionary;
import pl.sop.entities.Request;
import pl.sop.entities.User;
import pl.sop.entities.organizationStructure.CollegeService;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.entities.organizationStructure.InstituteService;
import pl.sop.enums.ERequestStatus;
import pl.sop.repositories.CompanyRepository;
import pl.sop.repositories.RequestRepository;
import pl.sop.repositories.UserRepository;

@Service
public class RequestService {

  @Autowired
  private RequestRepository requestRepository;

  @Autowired
  private CompanyService companyService;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private CollegeService collegeService;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private UserService userService;

  @Autowired
  private DictionaryService dictionaryService;

  @Autowired
  private UserRepository userRepository;

  private DTOToRequestConverter dtoToRequestConverter = new DTOToRequestConverter();
  private RequestToDTOConverter requestToDTOConverter = new RequestToDTOConverter();


  public ResponseEntity<List<RequestDTO>> getAllRequestsForStudent(Long internId) {
    List<Request> requests = this.requestRepository.getAllRequestsForStudent(internId);
    List<RequestDTO> requestDTOS = requests.stream().map(requestToDTOConverter::convert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(requestDTOS);
  }

  public ResponseEntity<List<RequestDTO>> getAllRequestsForInstitute(Long userId) {
    List<Long> instituteIds = instituteService.getAllInstitutesIdForUser(userId);
    List<Request> requests = requestRepository.getAllRequestsForInstitutes(instituteIds);

    List<RequestDTO> requestDTOS = requests.stream().map(requestToDTOConverter::convert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(requestDTOS);
  }

  public ResponseEntity<List<RequestDTO>> getAllRequestsForDirectorId(Long directorId) {
    List<Request> requests = requestRepository.getAllRequestsForDirectorId(directorId);

    List<RequestDTO> requestDTOS = requests.stream().map(requestToDTOConverter::convert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(requestDTOS);
  }

  public ResponseEntity<List<RequestDTO>> getAllRequestsForCollege(Long selectedCollegeId) {
    List<Request> requests = this.requestRepository.getAllRequestsForCollege(selectedCollegeId);
    List<RequestDTO> requestDTOS = requests.stream().map(requestToDTOConverter::convert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(requestDTOS);
  }

  public ResponseEntity<RequestDTO> getRequestById(Long id) {
    Request request = this.requestRepository.getRequestById(id);
    RequestDTO requestDTO = requestToDTOConverter.convert(request);
    return ResponseEntity.ok(requestDTO);
  }

  public ResponseEntity createRequest(RequestDTO requestDTO) {
    if (!companyService.isNipValid(requestDTO.getNip())) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    Request request = dtoToRequestConverter.convert(requestDTO);

    User moderator = userService.findUser(requestDTO.getModeratorId());
    if (request != null) {
      request.setModerator(moderator);
    }
    request.setActualRequestStatus(ERequestStatus.SENT);
    request.setModeratorDecisionStatus(ERequestStatus.AWAITING);

    Institute institute = instituteService.getById(requestDTO.getInstituteId());
    if (institute != null) {
      request.setInstitute(institute);
    }

    User intern = userService.findUser(requestDTO.getInternId());
    if (intern != null) {
      request.setIntern(intern);
    }

    request.setName(request.getName() + "/" + intern.getFirstName() + " " + intern.getLastName());
    request.setActive(Boolean.TRUE);

    Dictionary requestType = dictionaryService.getByValue(requestDTO.getRequestTypeName());
    if (requestType != null) {
      request.setRequestType(requestType);
    }

    if (companyService.checkIfCompanyExists(requestDTO.getNip())) {
      Company company = companyService.getCompanyByNip(requestDTO.getNip());
      request.setCompany(company);
    } else {
      Company company = new Company();
      company.setName(requestDTO.getCompanyName());
      company.setDescription(requestDTO.getDescription());
      company.setNip(requestDTO.getNip());
      company.setActive(Boolean.TRUE);
      company.setDeleted(Boolean.FALSE);
      this.companyRepository.save(company);
      request.setCompany(company);
    }

    this.requestRepository.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  public ResponseEntity updateAsModerator(RequestDTO requestDTO) {
    Request request = requestRepository.getRequestById(requestDTO.getId());
    request.setModeratorDecisionDate(requestDTO.getModeratorDecisionDate());
    request.setModeratorDecisionStatus(requestDTO.getModeratorDecisionStatus());
    request.setModeratorDecisionFeedback(requestDTO.getModeratorDecisionFeedback());
    if (request.getModeratorDecisionStatus().equals(ERequestStatus.ACCEPTED)) {
      request.setAdminDecisionStatus(ERequestStatus.AWAITING);
      request.setActualRequestStatus(ERequestStatus.AWAITING);
      User admin = userRepository.findUserById(requestDTO.getAdminId());
      request.setAdmin(admin);
    } else {
      request.setAdminDecisionStatus(null);
      request.setActualRequestStatus(ERequestStatus.REJECTED);
    }
    this.requestRepository.save(request);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  public ResponseEntity updateAsAdmin(RequestDTO requestDTO) {
    Request request = requestRepository.getRequestById(requestDTO.getId());
    request.setAdminDecisionDate(requestDTO.getAdminDecisionDate());
    request.setAdminDecisionStatus(requestDTO.getAdminDecisionStatus());
    request.setAdminDecisionFeedback(requestDTO.getAdminDecisionFeedback());
    request.setActualRequestStatus(requestDTO.getAdminDecisionStatus());
    this.requestRepository.save(request);
    if (request.getAdminDecisionStatus().equals(ERequestStatus.ACCEPTED)) {
      User intern = request.getIntern();
      intern.setDuringInternship(Boolean.TRUE);
      this.userRepository.save(intern);
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  public ResponseEntity deleteRequest(Long id) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

// Student wypełnia dane, wybiera moderatora (kierownika praktyk)
// Kierownik praktyk dostaje wniosek, sprawdza go
// Do zrobienia okienko przesyłające wniosek do admina
// admin (osoba z instytutu) dostaje wniosek, sprawdza go
// To samo okienko co moderator (info z podsumowania), możliwość dodania feedbacku,
// Może zaakceptować, wtedy leci do studenta i moderatora info
// Może odrzucić, wtedy leci do moderatora info
