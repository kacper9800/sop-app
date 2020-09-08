package pl.sop.organizationStructure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.ToDTO.CollegeToCollegeStructureDTOConverter;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.dto.CollegeStructureDTO;
import pl.sop.dto.CollegeStructureToSaveDTO;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.services.ActivationKeyService;
import pl.sop.services.UserService;

@Service
public class CollegeService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ActivationKeyService activationKeyService;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private DepartmentService departmentService;

  private final CollegeToCollegeStructureDTOConverter collegeToCollegeStructureDTOConverter = new CollegeToCollegeStructureDTOConverter();

  public CollegeService() {
  }

  public List<College> findAllColleges() {
    return collegeRepository.findAllColleges();
  }

  public List<College> findAllAvailableColleges() {
    return collegeRepository.findAllAvailableColleges();
  }

  public ResponseEntity<?> registerCollege(CollegeRegistrationDTO collegeRegistrationDTO) {
    if (!activationKeyService.isValidTokenForCollege(collegeRegistrationDTO.getCollegeId(),
        collegeRegistrationDTO.getToken())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Provided access token is wrong!"));
    }

    College college = collegeRepository.findCollegeById(collegeRegistrationDTO.getCollegeId());
    college.setActive(Boolean.TRUE);
    userService.registerUser(createCollegeAdmin(collegeRegistrationDTO), true);
    return ResponseEntity.ok(collegeRepository.save(college));
  }


  public SignUpRequest createCollegeAdmin(CollegeRegistrationDTO collegeRegistrationDTO) {
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setUsername(collegeRegistrationDTO.getEmail());
    signUpRequest.setEmail(collegeRegistrationDTO.getEmail());
    signUpRequest.setPassword(collegeRegistrationDTO.getPassword());
    signUpRequest.setCollegeId(collegeRegistrationDTO.getCollegeId());
    signUpRequest.setToken(collegeRegistrationDTO.getToken());
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_USER.toString());
    roles.add(ERole.ROLE_ADMIN.toString());
    signUpRequest.setRole(roles);
    return signUpRequest;
  }


  public void update(Long id, College college) {
    //ToDo
  }

  public CollegeStructureDTO findAllCollegeStructures(Long collegeId) {
    College college = this.collegeRepository.findCollegeById(collegeId);
    return collegeToCollegeStructureDTOConverter.convert(college);
  }

  public ResponseEntity<CollegeStructureToSaveDTO> createNewCollegeStructure(
      CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    College college = this.collegeRepository.findActiveCollegeStructureById(collegeStructureToSaveDTO.getCollegeId());
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
}
