package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.UserDTO;
import pl.sop.entities.User;

public class DTOToUserViewConverter implements Converter<UserDTO, User> {

  @Override
  public User convert(UserDTO input) {
    return null;
  }
}
