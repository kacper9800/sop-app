package pl.sop.entities.organizationStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeStructureToSaveDTO;

@Service
public class DepartmentService {

  @Autowired
  private InstituteRepository instituteRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  public Department findById(Long id) {
    return departmentRepository.findById(id).get();
  }

  public Department createNewDepartment(CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    Department department = new Department();
    department.setName(collegeStructureToSaveDTO.getStructureName());
    if (collegeStructureToSaveDTO.getParentId() != null) {
      Institute institute = instituteRepository
          .findActiveInstituteById(collegeStructureToSaveDTO.getParentId());
      department.setInstitute(institute);
    }
    department.setActive(Boolean.TRUE);
    department.setDeleted(Boolean.FALSE);
    return this.departmentRepository.save(department);
  }

  public void deleteDepartment(Long collegeStructureId) {
    this.departmentRepository.deleteById(collegeStructureId);
  }
}
