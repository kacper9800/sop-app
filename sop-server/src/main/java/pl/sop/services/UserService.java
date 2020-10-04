package pl.sop.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sop.entities.Role;
import pl.sop.entities.User;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeRepository;
import pl.sop.organizationStructure.DepartmentService;
import pl.sop.organizationStructure.FacultyService;
import pl.sop.organizationStructure.InstituteService;
import pl.sop.repositories.RoleRepository;
import pl.sop.repositories.UserRepository;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.entities.Token;

@Service
public class UserService {


  @Autowired
  public UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private ActivationKeyService activationKeyService;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private InstituteService instituteService;

  @Autowired
  private PasswordEncoder encoder;

  public User getUser(Long id) {
    return userRepository.findUserById(id);

  }

  public List<User> getAllUsers() {
    List<User> users = userRepository.findAllUsers();
    Comparator<User> comparator = (User u1, User u2) -> u1.getId().compareTo(u2.getId());
    Collections.sort(users, comparator);
    return users;
  }

  public ResponseEntity<?> registerUser(SignUpRequest signUpRequest, Boolean isAdminUser) {
    if (!isAdminUser) {
      if (checkUserToken(signUpRequest) != null) {
        return checkUserToken(signUpRequest);
      }
    }

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username already exist!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ROLE_ADMIN":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(adminRole);
            break;
          case "ROLE_MODERATOR":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }
    User user = prepareUser(signUpRequest, roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully"));
  }

  private User prepareUser(SignUpRequest signUpRequest, Set<Role> roles) {
    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));
    user.setFirstName(signUpRequest.getFirstName() != null? signUpRequest.getFirstName() : null);
    user.setLastName(signUpRequest.getLastName() != null? signUpRequest.getLastName() : null);
    user.setRoles(roles);
    user.setActive(Boolean.TRUE);
    College college = collegeRepository.findCollegeById(signUpRequest.getCollegeId());
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    Token token = activationKeyService.getTokenByValue(signUpRequest.getToken());
    prepareUserDataFromToken(user, token);
    token.setRemainingUses(token.getRemainingUses() -1);
    if (token.getRemainingUses() <= 0) {
      token.setActive(Boolean.FALSE);
    }
    return user;
  }

  private User prepareUserDataFromToken(User user, Token token) {
    if (token.getFaculty() != null) { // Jeśli jest zapisany w tokenie faculty id to dodaje go
      user.addFaculty(facultyService.findById(token.getFaculty().getId()));
      if (token.getInstitute() != null) { // Jeśli jest zapisany w tokenie institute id to dodaje go
        user.addInstitute(instituteService.findById(token.getInstitute().getId()));
        if (token.getDepartment() != null) { // Jeśli jest zapisany w tokenie departmen id to dodaje go
          user.addDepartment(departmentService.findById(token.getDepartment().getId()));
        }
      }
    }
    return user;
  }
  private ResponseEntity<MessageResponse> checkUserToken(SignUpRequest signUpRequest) {
    if (signUpRequest.getToken() == null) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Provided access token is null!"));
    }

    if (!activationKeyService.isValidTokenForUser(signUpRequest.getToken())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Access token is wrong!"));
    }
    return null;
  }

  public ResponseEntity changeCollege(Long userId, Long selectedCollegeId) {
    User user = userRepository.findUserById(userId);
    user.setSelectedCollegeId(selectedCollegeId);
    return ResponseEntity.ok(userRepository.save(user));
  }
}
