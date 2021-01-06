package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.UserModeratorDTO;
import pl.sop.entities.User;

public class UserToUserModeratorDTO implements Converter<User, UserModeratorDTO> {


  @Override
  public UserModeratorDTO convert(User input) {
    UserModeratorDTO userModeratorDTO = new UserModeratorDTO();
    userModeratorDTO.setId(input.getId());
    userModeratorDTO.setFirstName(input.getFirstName());
    userModeratorDTO.setLastName(input.getLastName());
    userModeratorDTO.setAcademicTitle(input.getAcademicTitle());
    return userModeratorDTO;
  }
}
