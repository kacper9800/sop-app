package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.TokenDTO;
import pl.sop.token.Token;

public class DTOToActivationKeyConverter implements Converter<TokenDTO, Token> {

  @Override
  public Token convert(TokenDTO input) {
    Token token = new Token();
    token.setValue(input.getValue());
    token.setExpirationDate(input.getExpirationDate());
    token.setRemainingUses(input.getRemainingUses());
    token.setCollegeId(input.getCollegeId());
    token.setFacultyId(input.getFacultyId());
    token.setInstituteId(input.getInstituteId());
    token.setDepartmentId(input.getDepartmentId());
    return token;
  }
}
