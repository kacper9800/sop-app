package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.entities.ActivationKey;

public class ActivationKeyToDTOConverter implements Converter<ActivationKey, ActivationKeyDTO> {

  @Override
  public ActivationKeyDTO convert(ActivationKey input) {
    ActivationKeyDTO activationKeyDTO = new ActivationKeyDTO();
    activationKeyDTO.setValue(input.getValue());
    activationKeyDTO.setStartExpirationDate(input.getStartExpirationDate());
    activationKeyDTO.setEndExpirationDate(input.getEndExpirationDate());
    activationKeyDTO.setNumberOfUses(input.getNumberOfUses());
    // ToDo
    //    tokenDTO.setCreatedBy(input.getCreatedBy().getId());
    if (input.getCollege() != null) {
      activationKeyDTO.setCollegeId(input.getCollege().getId());
      activationKeyDTO.setCollegeName(input.getCollege().getName());
    }
    if (input.getFaculty() != null) {
      activationKeyDTO.setFacultyId(input.getFaculty().getId());
      activationKeyDTO.setFacultyName(input.getFaculty().getName());
    }
    if (input.getInstitute() != null) {
      activationKeyDTO.setInstituteId(input.getInstitute().getId());
      activationKeyDTO.setInstituteName(input.getInstitute().getName());
    }
    if (input.getDepartment() != null) {
      activationKeyDTO.setDepartmentId(input.getDepartment().getId());
      activationKeyDTO.setDepartmentName(input.getDepartment().getName());
    }
    activationKeyDTO.setActive(input.isActive());
    return activationKeyDTO;
  }
}
