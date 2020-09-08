package pl.sop.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeStructureToSaveDTO;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  public Department getById(Long id) {
   return departmentRepository.findById(id).get();
  }

  public CollegeStructureToSaveDTO createNewDepartment(CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    return collegeStructureToSaveDTO;
  }
}
