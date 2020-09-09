package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.TokenDTO;
import pl.sop.entities.Token;

public class ActivationKeyToDTOConverter implements Converter<Token, TokenDTO> {

  @Override
  public TokenDTO convert(Token input) {
    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setValue(input.getValue());
    tokenDTO.setExpirationDate(input.getExpirationDate());
    tokenDTO.setRemainingUses(input.getRemainingUses());

    // To Do
//    tokenDTO.setCreatedBy(input.getCreatedBy().getId());
//    if (input)
    if (input.getCollege() != null) {
      tokenDTO.setCollegeName(input.getCollege().getName());
    }
    if (input.getFaculty() != null) {
      tokenDTO.setFacultyName(input.getFaculty().getName());
    }
    if (input.getInstitute() != null) {
      tokenDTO.setInstituteName(input.getInstitute().getName());
    }
    if (input.getDepartment() != null) {
      tokenDTO.setDepartmentName(input.getDepartment().getName());
    }
    tokenDTO.setActive(input.isActive());
    return tokenDTO;
  }
}
