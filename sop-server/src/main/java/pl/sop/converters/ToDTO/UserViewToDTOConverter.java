package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.UserDTO;
import pl.sop.entities.User;

public class UserViewToDTOConverter implements Converter<User, UserDTO> {

  @Override
  public UserDTO convert(User input) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(input.getId());
    userDTO.setAcademic_title(input.getAcademic_title());
    userDTO.setFirst_name(input.getFirstName());
    userDTO.setLast_name(input.getLastName());
    userDTO.setUsername(input.getUsername());
    userDTO.setPhone(input.getPhone());
    userDTO.setBirthDate(input.getBirthDate());
    return userDTO;
  }
}
