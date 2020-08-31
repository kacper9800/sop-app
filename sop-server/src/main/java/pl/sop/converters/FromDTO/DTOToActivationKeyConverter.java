package pl.sop.converters.FromDTO;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sop.converters.Converter;
import pl.sop.dto.TokenDTO;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeRepository;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.DepartmentRepository;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.FacultyRepository;
import pl.sop.organizationStructure.Institute;
import pl.sop.organizationStructure.InstituteRepository;
import pl.sop.entities.Token;

public class DTOToActivationKeyConverter implements Converter<TokenDTO, Token> {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private InstituteRepository instituteRepository;

  @Autowired
  private FacultyRepository facultyRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public Token convert(TokenDTO input) {
    Token token = new Token();
    token.setValue(input.getValue());
    token.setExpirationDate(input.getExpirationDate());
    token.setRemainingUses(input.getRemainingUses());
    College college = collegeRepository.findCollegeById(input.getCollegeId());
    token.setCollege(college);
    Faculty faculty = facultyRepository.findById(input.getFacultyId()).get();
    token.setFaculty(faculty);
    Institute institute = instituteRepository.findById(input.getInstituteId()).get();
    token.setInstitute(institute);
    Department department = departmentRepository.findById(input.getDepartmentId()).get();
    token.setDepartment(department);
    return token;
  }
}
