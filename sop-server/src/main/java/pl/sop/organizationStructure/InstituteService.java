package pl.sop.organizationStructure;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.ToDTO.InstituteToDTOConverter;
import pl.sop.dto.CollegeStructureToSaveDTO;
import pl.sop.dto.InstituteDTO;

@Service
public class InstituteService {

  @Autowired
  private FacultyRepository facultyRepository;

  @Autowired
  private InstituteRepository instituteRepository;

  private InstituteToDTOConverter instituteToDTOConverter = new InstituteToDTOConverter();

  public Institute findById(Long id) {
    return instituteRepository.findById(id).get();
  }

  public Institute createNewInstitute(CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    Institute institute = new Institute();
    institute.setName(collegeStructureToSaveDTO.getStructureName());
    if (collegeStructureToSaveDTO.getParentId() != null) {
      Faculty faculty = facultyRepository
          .findActiveFacultyById(collegeStructureToSaveDTO.getParentId());
      institute.setFaculty(faculty);
    }
    institute.setActive(Boolean.TRUE);
    institute.setDeleted(Boolean.FALSE);
    return this.instituteRepository.save(institute);
  }

  public void deleteInstitute(Long collegeStructureId) {
    this.instituteRepository.deleteById(collegeStructureId);
  }

  public ResponseEntity<List<InstituteDTO>> getAllInstitutesForCollege(Long collegeId) {
    List<Institute> institutes = instituteRepository.findAllForCollegeId(collegeId);
    List<InstituteDTO> instituteDTOS = institutes.stream().map(instituteToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(instituteDTOS);
  }

  public Institute getById(Long instituteId) {
    Institute institute = instituteRepository.findActiveInstituteById(instituteId);
    return institute;
  }
}
