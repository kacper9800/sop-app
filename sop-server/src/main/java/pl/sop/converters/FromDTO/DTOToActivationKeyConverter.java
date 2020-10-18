package pl.sop.converters.FromDTO;

import org.springframework.stereotype.Service;
import pl.sop.converters.Converter;
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

@Service
public class DTOToActivationKeyConverter implements Converter<ActivationKeyDTO, ActivationKey> {

  private CollegeService collegeService;
  private InstituteService instituteService;
  private FacultyService facultyService;
  private DepartmentService departmentService;

  public DTOToActivationKeyConverter(CollegeService collegeService,
      InstituteService instituteService, FacultyService facultyService,
      DepartmentService departmentService) {
    this.collegeService = collegeService;
    this.instituteService = instituteService;
    this.facultyService = facultyService;
    this.departmentService = departmentService;
  }

  @Override
  public ActivationKey convert(ActivationKeyDTO input) {
    ActivationKey activationKey = new ActivationKey();
    activationKey.setId(input.getId());
    activationKey.setValue(input.getValue());
    activationKey.setStartExpirationDate(input.getStartExpirationDate());
    activationKey.setEndExpirationDate(input.getEndExpirationDate());
    activationKey.setNumberOfUses(input.getNumberOfUses());
    College college = collegeService.findById(input.getCollegeId());
    activationKey.setCollege(college);
    if (input.getFacultyId() != null) {
      Faculty faculty = facultyService.findById(input.getFacultyId());
      activationKey.setFaculty(faculty);
    }
    if (input.getInstituteId() != null) {
      Institute institute = instituteService.findById(input.getInstituteId());
      activationKey.setInstitute(institute);
    }
    if (input.getDepartmentId() != null) {
      Department department = departmentService.findById(input.getDepartmentId());
      activationKey.setDepartment(department);
    }
    return activationKey;
  }
}
