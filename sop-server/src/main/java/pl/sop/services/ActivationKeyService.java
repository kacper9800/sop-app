package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToActivationKeyConverter;
import pl.sop.converters.ToDTO.ActivationKeyToDTOConverter;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.entities.Direction;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.CollegeService;
import pl.sop.entities.organizationStructure.Department;
import pl.sop.entities.organizationStructure.DepartmentService;
import pl.sop.entities.organizationStructure.Faculty;
import pl.sop.entities.organizationStructure.FacultyService;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.entities.organizationStructure.InstituteService;
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

  @Autowired
  private DirectionService directionService;

  private final ActivationKeyToDTOConverter activationKeyToDTOConverter = new ActivationKeyToDTOConverter();
  private final DTOToActivationKeyConverter dtoToActivationKeyConverter
      = new DTOToActivationKeyConverter(collegeService, directionService, instituteService,
      facultyService, departmentService);

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

  public ResponseEntity<ActivationKeyDTO> getActivationKeyByValue(String activationKeyValue) {
    ActivationKey activationKey = activationKeyRepository
        .findValidActivationKeyByValue(activationKeyValue);
    ActivationKeyDTO activationKeyDTO = activationKeyToDTOConverter.convert(activationKey);
    return ResponseEntity.ok(activationKeyDTO);
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

  public ResponseEntity deleteActivationKey(Long id) {
    this.activationKeyRepository.deleteActivationKeyById(id);
    return ResponseEntity.ok(true);
  }

  public void deactivateActivationKey(ActivationKey activationKey) {
    activationKey.setActive(Boolean.FALSE);
    activationKey.setNumberOfUses(0);
  }

  public ActivationKey saveActivationKey(ActivationKey activationKey) {
    return this.activationKeyRepository.save(activationKey);
  }

  public ResponseEntity<ActivationKey> createNewActivationKeyForCollege(
      ActivationKeyDTO activationKeyDTO) {
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

  public ActivationKey createNewActivationKey(ActivationKeyDTO tokenDTO) {
    ActivationKey activationKey = new ActivationKey();
    Department department = null;
    Direction direction = null;
    Institute institute = null;
    Faculty faculty = null;
    College college = null;
    activationKey.setValue(tokenDTO.getValue());
    activationKey.setRole(tokenDTO.getRole());

    if (tokenDTO.getDirectionId() != null) {
      direction = directionService.getById(tokenDTO.getDirectionId());
      if (direction != null) {
        college = direction.getInstitute().getFaculty().getCollege();
        faculty = direction.getInstitute().getFaculty();
        institute = direction.getInstitute();
      }
    } else {
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
    }
    if (direction != null) {
      activationKey.setDirection(direction);
    }
    if (department != null) {
      activationKey.setDepartment(department);
    }
    if (institute != null) {
      activationKey.setInstitute(institute);
    }
    if (faculty != null) {
      activationKey.setFaculty(faculty);
    }
    if (college != null) {
      activationKey.setCollege(college);
    }
    activationKey.setStartExpirationDate(tokenDTO.getStartExpirationDate());
    activationKey.setEndExpirationDate(tokenDTO.getEndExpirationDate());
    activationKey.setActive(Boolean.TRUE);
    activationKey.setNumberOfUses(tokenDTO.getNumberOfUses());
    return saveActivationKey(activationKey);
  }

  public ResponseEntity<ActivationKeyDTO> updateActivationKey(ActivationKeyDTO activationKeyDTO) {
    if (activationKeyDTO != null) {
//      this.activationKeyRepository.save(activationKeyDTO);
      return ResponseEntity.ok(activationKeyDTO);
    }
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
  }

}
