package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.ToDTO.ActivationKeyToDTOConverter;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeService;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.DepartmentService;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.FacultyService;
import pl.sop.organizationStructure.Institute;
import pl.sop.organizationStructure.InstituteService;
import pl.sop.repositories.ActivationKeyRepository;

@Service
public class ActivationKeyService {

  @Autowired
  private ActivationKeyRepository activationKeyRepository;

  @Autowired
  private CollegeService collegeService;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  private DepartmentService departmentService;

  private final ActivationKeyToDTOConverter activationKeyToDTOConverter = new ActivationKeyToDTOConverter();

  public ResponseEntity getAllTokens() {
    List<ActivationKey> activationKeys = activationKeyRepository.getAllActivationKeys();
    List<ActivationKeyDTO> activationKeyDTOS = activationKeys.stream().map(activationKey ->
        activationKeyToDTOConverter.convert(activationKey)).collect(Collectors.toList());
    return new ResponseEntity(activationKeyDTOS, HttpStatus.OK);
  }

  public ResponseEntity getAllTokensForCollege(Long id) {
    List<ActivationKey> tokens = activationKeyRepository.getAllActivationKeysByCollegeId(id);
    List<ActivationKeyDTO> tokenDTOS = tokens.stream().map(token ->
        activationKeyToDTOConverter.convert(token)).collect(Collectors.toList());
    return new ResponseEntity(tokenDTOS, HttpStatus.OK);
  }

//  public ResponseEntity getAllTokensForCompany(Long id) {
//    List<Token> tokens = activationKeyRepository.getAllTokensByCompanyId(id);
//    List<TokenDTO> tokenDTOS = tokens.stream().map(token ->
//        activationKeyToDTOConverter.convert(token)).collect(Collectors.toList());
//    return new ResponseEntity(tokenDTOS, HttpStatus.OK);
//  }

  public ActivationKey getTokenByValue(String tokenValue) {
    return activationKeyRepository.findValidActivationKeyByValue(tokenValue);
  }

  public boolean isValidTokenForUser(String tokenValue) {
    ActivationKey token = activationKeyRepository.findValidActivationKeyByValue(tokenValue);
    if (token != null) {
      return true;
    }
    return false;
  }

  public boolean isValidTokenForCollege(Long collegeId, String tokenValue) {
    ActivationKey token = activationKeyRepository.findValidActivationKeyByValue(tokenValue);
    if (token != null && token.getCollege().getId().equals(collegeId)) {
      return true;
    }
    return false;
  }

  public void deactivateActivationKey(ActivationKey activationKey) {
    activationKey.setActive(Boolean.FALSE);
    activationKey.setNumberOfUses(0);
  }

  public ActivationKey saveActivationKey(ActivationKey activationKey) {
    return this.activationKeyRepository.save(activationKey);
  }

  public ResponseEntity<ActivationKey> createNewActivationKeyForCollege(ActivationKeyDTO activationKeyDTO) {
    ActivationKey activationKey = new ActivationKey();
    if (activationKeyDTO.getValue() == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    activationKey.setValue(activationKeyDTO.getValue());
    activationKey.setStartExpirationDate(activationKeyDTO.getStartExpirationDate());
    activationKey.setEndExpirationDate(activationKeyDTO.getEndExpirationDate());
    if (activationKeyDTO.getCollegeId() != null) {
      College college = collegeService.findById(activationKeyDTO.getCollegeId());
      activationKey.setCollege(college);
    }
    activationKey.setActive(Boolean.TRUE);
    activationKey.setNumberOfUses(activationKeyDTO.getNumberOfUses());
    return ResponseEntity.ok(saveActivationKey(activationKey));
  }

  public ResponseEntity<ActivationKey> createNewActivationKey(ActivationKeyDTO tokenDTO) {
    ActivationKey activationKey = new ActivationKey();
    activationKey.setValue(tokenDTO.getValue());
    Department department = new Department();
    Institute institute = new Institute();
    Faculty faculty = new Faculty();
    College college = new College();
    if (tokenDTO.getDepartmentId() != null) {
      department = departmentService.findById(tokenDTO.getDepartmentId());
      institute = department.getInstitute();
      faculty = institute.getFaculty();
      college = faculty.getCollege();
    } else if (tokenDTO.getDepartmentId() == null && tokenDTO.getInstituteId() != null) {
      department = null;
      institute = instituteService.findById(tokenDTO.getInstituteId());
      faculty = institute.getFaculty();
      college = faculty.getCollege();
    } else if (tokenDTO.getDepartmentId() == null && tokenDTO.getInstituteId() == null
        && tokenDTO.getFacultyId() != null) {
      department = null;
      institute = null;
      faculty = facultyService.findById(tokenDTO.getFacultyId());
      college = faculty.getCollege();
    } else if (tokenDTO.getDepartmentId() == null && tokenDTO.getInstituteId() == null
        && tokenDTO.getFacultyId() == null && tokenDTO.getCollegeId() != null) {
      department = null;
      institute = null;
      faculty = null;
      college = collegeService.findById(tokenDTO.getCollegeId());
    }
    activationKey.setDepartment(department);
    activationKey.setInstitute(institute);
    activationKey.setFaculty(faculty);
    activationKey.setCollege(college);
//    token.setActive(tokenDTO.getActive());
    activationKey.setActive(Boolean.TRUE);
    activationKey.setNumberOfUses(tokenDTO.getNumberOfUses());
    return ResponseEntity.ok(saveActivationKey(activationKey));
  }
}
