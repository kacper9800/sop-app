package pl.sop.converters.FromDTO;

import org.springframework.stereotype.Service;
import pl.sop.converters.Converter;
import pl.sop.dto.TokenDTO;
import pl.sop.entities.Token;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeService;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.DepartmentService;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.FacultyService;
import pl.sop.organizationStructure.Institute;
import pl.sop.organizationStructure.InstituteService;

@Service
public class DTOToActivationKeyConverter implements Converter<TokenDTO, Token> {

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
  public Token convert(TokenDTO input) {
    Token token = new Token();
    token.setValue(input.getValue());
    token.setExpirationDate(input.getExpirationDate());
    token.setRemainingUses(input.getRemainingUses());
    College college = collegeService.findById(input.getCollegeId());
    token.setCollege(college);
    if (input.getFacultyId() != null) {
      Faculty faculty = facultyService.findById(input.getFacultyId());
      token.setFaculty(faculty);
    }
    if (input.getInstituteId() != null) {
      Institute institute = instituteService.findById(input.getInstituteId());
      token.setInstitute(institute);
    }
    if (input.getDepartmentId() != null) {
      Department department = departmentService.findById(input.getDepartmentId());
      token.setDepartment(department);
    }

    return token;
  }
}
