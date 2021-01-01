package pl.sop.entities.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeStructureToSaveDTO;

@Service
public class FacultyService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private FacultyRepository facultyRepository;

  public Faculty findById(Long id) {
    return this.facultyRepository.findById(id).get();
  }

  public Faculty createNewFaculty(CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    Faculty faculty = new Faculty();
    faculty.setName(collegeStructureToSaveDTO.getStructureName());
    if (collegeStructureToSaveDTO.getParentId() != null) {
      College college = collegeRepository.findActiveCollegeById(collegeStructureToSaveDTO.getParentId());
      faculty.setCollege(college);
    }
    faculty.setActive(Boolean.TRUE);
    faculty.setDeleted(Boolean.FALSE);
    return this.facultyRepository.save(faculty);
  }

  public void deleteFaculty(Long id) {
    this.facultyRepository.deleteById(id);
  }
}
