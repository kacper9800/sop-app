package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.DepartmentDTO;
import pl.sop.organizationStructure.Department;

public class DepartmentToDTOConverter implements Converter<Department, DepartmentDTO> {

  @Override
  public DepartmentDTO convert(Department input) {
    DepartmentDTO departmentDTO = new DepartmentDTO();
    departmentDTO.setId(input.getId());
    departmentDTO.setName(input.getName());
    return departmentDTO;
  }
}
