package pl.sop.organizationStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToActivationKeyConverter;
import pl.sop.converters.FromDTO.DTOToCollegeConverter;
import pl.sop.converters.ToDTO.CollegeToCollegeStructureDTOConverter;
import pl.sop.converters.ToDTO.CollegeToDTOConverter;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.dto.CollegeDTO;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.dto.CollegeStructureDTO;
import pl.sop.dto.CollegeStructureToSaveDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.services.ActivationKeyService;
import pl.sop.services.DirectionService;
import pl.sop.services.UserService;

@Service
public class CollegeService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private UserService userService;

  @Autowired
  private ActivationKeyService activationKeyService;

  @Autowired
  private DirectionService directionService;



  private final DTOToActivationKeyConverter dtoToActivationKeyConverter = new DTOToActivationKeyConverter(
      this, directionService, instituteService, facultyService, departmentService);

  private final DTOToCollegeConverter dtoToCollegeConverter = new DTOToCollegeConverter();

  private final CollegeToDTOConverter collegeToDTOConverter = new CollegeToDTOConverter();

  private final CollegeToCollegeStructureDTOConverter collegeToCollegeStructureDTOConverter = new CollegeToCollegeStructureDTOConverter();

  public CollegeService() {
  }

  public College findById(Long id) {
    return this.collegeRepository.findById(id).get();
  }

  public CollegeDTO findCollegeById(Long id) {
    College college = this.collegeRepository.findById(id).get();
    CollegeDTO collegeDTO = collegeToDTOConverter.convert(college);
    return collegeDTO;
  }

  public List<CollegeDTO> findAllColleges() {
    List<College> colleges = collegeRepository.findAllColleges();
    List<CollegeDTO> collegeDTOS = new ArrayList<>();
    for (College college : colleges) {
      collegeDTOS.add(collegeToDTOConverter.convert(college));
    }
    collegeDTOS.sort(Comparator.comparing(CollegeDTO::getId));
    return collegeDTOS;
  }

  public List<CollegeDTO> findAllAvailableColleges() {
    List<College> colleges = collegeRepository.findAllAvailableColleges();
    List<CollegeDTO> collegeDTOS = new ArrayList<>();
    for (College college : colleges) {
      collegeDTOS.add(collegeToDTOConverter.convert(college));
    }
    return collegeDTOS;
  }

  public void createNewCollege(CollegeDTO collegeDTO) {
    College college = dtoToCollegeConverter.convert(collegeDTO);
    this.collegeRepository.save(college);
  }

  public ResponseEntity<HttpStatus> activateCollege(ActivationKeyDTO activationKeyDTO) {
    if (activationKeyDTO != null && activationKeyDTO.getValue() != null) {
      College college = collegeRepository.findOnlyCollegeById(activationKeyDTO.getCollegeId());
      ActivationKey activationKey = dtoToActivationKeyConverter.convert(activationKeyDTO);
      activationKey.setCollege(college);
      this.activationKeyService.saveActivationKey(activationKey);

    }
    return ResponseEntity.ok(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<?> registerCollege(CollegeRegistrationDTO collegeRegistrationDTO) {
    if (!activationKeyService.isValidTokenForCollege(collegeRegistrationDTO.getCollegeId(),
        collegeRegistrationDTO.getActivationKey())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Provided access token is wrong!"));
    }
    ActivationKey activationKey = this.activationKeyService.getTokenByValue(collegeRegistrationDTO.getActivationKey());
    College college = collegeRepository.findCollegeById(collegeRegistrationDTO.getCollegeId());
    college.setActive(Boolean.TRUE);
    userService.registerUser(createCollegeAdmin(collegeRegistrationDTO), true);
    this.activationKeyService.deactivateActivationKey(activationKey);
    this.activationKeyService.saveActivationKey(activationKey);
    return ResponseEntity.ok(collegeRepository.save(college));
  }


  public SignUpRequest createCollegeAdmin(CollegeRegistrationDTO collegeRegistrationDTO) {
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setUsername(collegeRegistrationDTO.getEmail());
    signUpRequest.setEmail(collegeRegistrationDTO.getEmail());
    signUpRequest.setPassword(collegeRegistrationDTO.getPassword());
    signUpRequest.setCollegeId(collegeRegistrationDTO.getCollegeId());
    signUpRequest.setToken(collegeRegistrationDTO.getActivationKey());
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_USER.toString());
    roles.add(ERole.ROLE_ADMIN.toString());
    signUpRequest.setRole(roles);
    return signUpRequest;
  }

  public void update(Long id, College college) {
    //ToDo
  }

  public void delete(Long id) {
    this.collegeRepository.deleteCollegeById(id);
  }

  public CollegeStructureDTO findAllCollegeStructures(Long collegeId) {
    College college = this.collegeRepository.findActiveCollegeStructureById(collegeId);
    return collegeToCollegeStructureDTOConverter.convert(college);
  }

  public ResponseEntity createNewCollegeStructure(
      CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    College college = this.collegeRepository
        .findActiveCollegeStructureById(collegeStructureToSaveDTO.getCollegeId());
    switch (collegeStructureToSaveDTO.getLevel()) {
      case 1:
        return new ResponseEntity(this.facultyService.createNewFaculty(collegeStructureToSaveDTO),
            HttpStatus.OK);
      case 2:
        return new ResponseEntity(
            this.instituteService.createNewInstitute(collegeStructureToSaveDTO), HttpStatus.OK);
      case 3:
        return new ResponseEntity(
            this.departmentService.createNewDepartment(collegeStructureToSaveDTO), HttpStatus.OK);
    }
    return null;
  }

  public void deleteCollegeStructure(Long collegeStructureId, String collegeStructure) {
    switch (collegeStructure) {
      case "FACULTY":
        facultyService.deleteFaculty(collegeStructureId);
        break;
      case "INSTITUTE":
        instituteService.deleteInstitute(collegeStructureId);
        break;
      case "DEPARTMENT":
        departmentService.deleteDepartment(collegeStructureId);
        break;
    }
  }

  public ResponseEntity<Boolean> changeCollegeActiveStatus(Boolean newStatus, Long collegeId) {
    College college = collegeRepository.findById(collegeId).get();
    college.setActive(newStatus);
    collegeRepository.save(college);
    return ResponseEntity.ok(newStatus);
  }

  public void updateCollege(CollegeDTO collegeDTO) {
//    College actualCollege = collegeRepository.findCollegeById(collegeDTO.getId());
//    actualCollege.set

  }
}
