package pl.sop.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeStructureToSaveDTO;

@Service
public class FacultyService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private FacultyRepository facultyRepository;

  public Faculty getById(Long id) {
    return facultyRepository.findById(id).get();
  }

  public Faculty createNewFaculty(CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    Faculty faculty = new Faculty();
    faculty.setName(collegeStructureToSaveDTO.getStructureName());
    if (collegeStructureToSaveDTO.getCollegeId() != null) {
      College college = collegeRepository.findActiveCollegeById(collegeStructureToSaveDTO.getParentId());
      faculty.setCollege(college);
    }
    faculty.setActive(Boolean.TRUE);
    faculty.setDeleted(Boolean.FALSE);
    return this.facultyRepository.save(faculty);
  }
}
