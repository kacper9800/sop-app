package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sop.dto.UserDTO;
import pl.sop.repositories.UserRepository;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.UserService;

@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @GetMapping(value = "/api/users")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_MODERATOR')")
  public ResponseEntity<List<UserDTO>> getAllUsers(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    List<UserDTO> users;
    if (user.getAuthorities().stream().anyMatch(
        grantedAuthorities -> grantedAuthorities.getAuthority()
            .equals("ROLE_SUPERADMIN"))) {
      users = userService.getAllUsers();
    } else {
      users = userService.getAllUsersForCollegeId(user.getSelectedCollegeId());
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping(value = "/api/tests")
  public String getTest() {
    return "ok";
  }

  @GetMapping(value = "/api/users/changeCollege/{id}")
  public ResponseEntity changeCollege(Authentication authentication, @PathVariable("id") Long id) {
    if (id == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    return userService.changeCollege(user.getId(), id);
  }

  // Returns for logged user
  @GetMapping(value = "/api/institute/moderators")
  public ResponseEntity<List<UserDTO>> getModeratorsForInstitute(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    Long userId = user.getId();
    if (userId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return userService.getAllModeratorsForInstitute(userId);
  }

  // Returns for logged user
  @GetMapping(value = "/api/institute/admins")
  public ResponseEntity<List<UserDTO>> getAdminsForInstitute(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    Long userId = user.getId();
    if (userId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return userService.getAllAdminsForInstitute(userId);
  }

}
