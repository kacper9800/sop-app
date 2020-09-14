package pl.sop.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeStructureToSaveDTO;

@Service
public class InstituteService {

  @Autowired
  private FacultyRepository facultyRepository;

  @Autowired
  private InstituteRepository instituteRepository;

  public Institute getById(Long id) {
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
}
