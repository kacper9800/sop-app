package pl.sop.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sop.configuration.mail.EmailServiceImpl;
import pl.sop.converters.ToDTO.UserToUserModeratorDTO;
import pl.sop.converters.ToDTO.UserViewToDTOConverter;
import pl.sop.dto.UserDTO;
import pl.sop.dto.UserModeratorDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.entities.Role;
import pl.sop.entities.User;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.CollegeRepository;
import pl.sop.entities.organizationStructure.DepartmentService;
import pl.sop.entities.organizationStructure.FacultyService;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.entities.organizationStructure.InstituteService;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.repositories.RoleRepository;
import pl.sop.repositories.UserRepository;

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

  @Autowired
  private EmailServiceImpl emailService;

  private final UserViewToDTOConverter userViewToDTOConverter = new UserViewToDTOConverter();
  private final UserToUserModeratorDTO userToUserModeratorDTO = new UserToUserModeratorDTO();

  public User findUser(Long id) {
    User user = userRepository.findUserById(id);
    return user;
  }

  public UserDTO getInternBasicData(Long id) {
    User user = userRepository.findUserById(id);
    UserDTO userDTO = userViewToDTOConverter.convert(user);
    return userDTO;
  }

  public UserDTO getUser(Long id) {
    User user = userRepository.findUserById(id);
    return userViewToDTOConverter.convert(user);
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAllUsers();
    Comparator<User> comparator = Comparator.comparing(User::getId);
    Collections.sort(users, comparator);
    List<UserDTO> userDTOS = users.stream().map(userViewToDTOConverter::convert).collect(
        Collectors.toList());
    return userDTOS;
  }

  public List<UserDTO> getAllUsersForCollegeId(Long collegeId) {
    List<User> users = userRepository.findAllUsersForCollegeId(collegeId);
    Comparator<User> comparator = Comparator.comparing(User::getId);
    Collections.sort(users, comparator);
    List<UserDTO> userDTOS = users.stream().map(userViewToDTOConverter::convert).collect(
        Collectors.toList());
    return userDTOS;
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

    ActivationKey activationKey = activationKeyService.getTokenByValue(signUpRequest.getToken());
    College college = activationKey.getCollege();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
    ERole eRole = ERole.valueOf(activationKey.getRole());
    Role specificRole = roleRepository.findByName(eRole).get();
    Set<Role> roles = new HashSet<>();
    roles.add(userRole);
    roles.add(specificRole);
    User user = prepareUser(signUpRequest, roles, college);
    userRepository.save(user);
//    emailService.sendSimpleMessage(user.getEmail(), "System obsługi praktyk",
//        "Rejestracja pomyślnie zakończona\n"
//            + "Twoja nazwa użytkownika: " + user.getUsername());
    return ResponseEntity.ok(new MessageResponse("User registered successfully"));
  }

  private User prepareUser(SignUpRequest signUpRequest, Set<Role> roles, College college) {
    User user = new User();
    user.setUsername(getNextUsername(signUpRequest.getFirstName(), signUpRequest.getLastName()));
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));
    user.setFirstName(signUpRequest.getFirstName() != null ? signUpRequest.getFirstName() : null);
    user.setLastName(signUpRequest.getLastName() != null ? signUpRequest.getLastName() : null);
    user.setRoles(roles);
    user.setActive(Boolean.TRUE);
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    ActivationKey activationKey = activationKeyService.getTokenByValue(signUpRequest.getToken());
    prepareUserDataFromToken(user, activationKey);
    activationKey.setNumberOfUses(activationKey.getNumberOfUses() - 1);
    if (activationKey.getNumberOfUses() <= 0) {
      activationKey.setActive(Boolean.FALSE);
    }
    return user;
  }

  private String getNextUsername(String firstName, String lastName) {
    String username =
        firstName.substring(0, 3).toLowerCase() + lastName.substring(0, 3).toLowerCase();
    String propsalUsername = username + '1';

    List<String> sameUserNames = new ArrayList<>();
    List<Long> numbers = new ArrayList<>();
    if (userRepository.existsByUsername(
        propsalUsername)) { // jeśli istnieją inne osoby, które mają takie same username
      sameUserNames = userRepository.findSameUserNames(username); // to znajdźmy je!
      for (String sameUserName : sameUserNames) {
        char[] chars = sameUserName.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
          if (Character.isDigit(c)) {
            sb.append(c);
          }
        }
        numbers.add(Long.parseLong(sb.toString()));
      }
      Long lastNumber = Collections.max(numbers);
      return username + ++lastNumber;
    } else {
      return propsalUsername;
    }
  }

  private User prepareUserDataFromToken(User user, ActivationKey activationKey) {
    if (activationKey.getFaculty()
        != null) { // Jeśli jest zapisany w tokenie faculty id to dodaje go
      user.addFaculty(facultyService.findById(activationKey.getFaculty().getId()));
      if (activationKey.getInstitute()
          != null) { // Jeśli jest zapisany w tokenie institute id to dodaje go
        user.addInstitute(instituteService.findById(activationKey.getInstitute().getId()));
        if (activationKey.getDepartment()
            != null) { // Jeśli jest zapisany w tokenie department id to dodaje go
          user.addDepartment(departmentService.findById(activationKey.getDepartment().getId()));
        }
      }
    }
    return user;
  }

  private ResponseEntity<MessageResponse> checkUserToken(SignUpRequest signUpRequest) {
    if (signUpRequest.getToken() == null) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Provided access token is empty  !"));
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

  public ResponseEntity<List<UserModeratorDTO>> getAllModeratorsForInstitute(Long userId) {
    // Getting user data
    User user = userRepository.findUserById(userId);
    // Collecting user institutes
    Set<Institute> institutes = user.getInstitutes();
    List<UserModeratorDTO> moderators = new ArrayList<>();
    if (institutes != null) { // If institutes = 0  || null
      for (Institute institute : institutes) { // For all institutes get all users with Moderator Role
        List<User> instituteModerators = userRepository
            .findModeratorByInstituteId(institute.getId());
        for (User instituteModerator : instituteModerators) {
          UserModeratorDTO userModeratorDTO = new UserModeratorDTO();
          userModeratorDTO.setId(instituteModerator.getId());
          userModeratorDTO.setFirstName(instituteModerator.getFirstName());
          userModeratorDTO.setLastName(instituteModerator.getLastName());
          userModeratorDTO.setAcademicTitle(instituteModerator.getAcademicTitle());
          userModeratorDTO.setInstituteId(institute.getId());
          userModeratorDTO.setInstituteName(institute.getName());
          moderators.add(userModeratorDTO);
        }
      }
    }
    return ResponseEntity.ok(moderators);
  }

  public ResponseEntity<List<UserModeratorDTO>> getAllDirectorsForInstitute(Long userId) {
    User user = userRepository.findUserById(userId);
    // Collecting user institutes
    Set<Institute> institutes = user.getInstitutes();
    List<UserModeratorDTO> admins = new ArrayList<>();
    if (institutes != null) { // If institutes = 0  || null
      for (Institute institute : institutes) { // For all institutes get all users with Director Role
        List<User> instituteModerators = userRepository
            .findDirectorsByInstituteId(institute.getId());
        for (User instituteModerator : instituteModerators) {
          UserModeratorDTO userAdminDTO = new UserModeratorDTO();
          userAdminDTO.setId(instituteModerator.getId());
          userAdminDTO.setFirstName(instituteModerator.getFirstName());
          userAdminDTO.setLastName(instituteModerator.getLastName());
          userAdminDTO.setAcademicTitle(instituteModerator.getAcademicTitle());
          userAdminDTO.setInstituteId(institute.getId());
          userAdminDTO.setInstituteName(institute.getName());
          admins.add(userAdminDTO);
        }
      }
    }
    return ResponseEntity.ok(admins);
  }
}
