package pl.sop.converters.ToDTO;

import java.util.List;
import java.util.stream.Collectors;
import pl.sop.converters.Converter;
import pl.sop.dto.DepartmentDTO;
import pl.sop.dto.InstituteDTO;
import pl.sop.organizationStructure.Institute;

public class InstituteToDTOConverter implements Converter<Institute, InstituteDTO> {

  private final DepartmentToDTOConverter departmentToDTOConverter = new DepartmentToDTOConverter();

  @Override
  public InstituteDTO convert(Institute input) {
    InstituteDTO instituteDTO = new InstituteDTO();
    instituteDTO.setId(input.getId());
    instituteDTO.setName(input.getName());
    if (input.getDepartments() != null) {
      List<DepartmentDTO> departmentDTOSList = input.getDepartments().stream().map(department ->
          departmentToDTOConverter.convert(department)).collect(Collectors.toList());
      instituteDTO.setDepartments(departmentDTOSList);
    } else {
      instituteDTO.setDepartments(null);
    }
    return instituteDTO;
  }
}
