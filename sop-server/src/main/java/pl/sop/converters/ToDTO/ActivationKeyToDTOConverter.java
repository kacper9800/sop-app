package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.TokenDTO;
import pl.sop.token.Token;

public class ActivationKeyToDTOConverter implements Converter<Token, TokenDTO> {

  @Override
  public TokenDTO convert(Token input) {
    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setValue(input.getValue());
    tokenDTO.setExpirationDate(input.getExpirationDate());
    tokenDTO.setRemainingUses(input.getRemainingUses());
    tokenDTO.setCreatedBy(input.getCreatedBy().getId());
    tokenDTO.setCollegeId(input.getCollegeId());
    tokenDTO.setFacultyId(input.getFacultyId());
    tokenDTO.setInstituteId(input.getInstituteId());
    tokenDTO.setDepartmentId(input.getDepartmentId());
    return tokenDTO;
  }
}
