package pl.sop.defaultConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.sop.entities.Role;
import pl.sop.entities.User;
import pl.sop.enums.ERole;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeRepository;
import pl.sop.repositories.RoleRepository;
import pl.sop.repositories.UserRepository;

@Service
public class DefaultConfigurationService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @PostConstruct
  private void createDefaultAccounts() {
    this.createDefaultCollege();    // Creating default college
    this.createSuperAdminAccount(); // Creating default superAdmin account

    this.createFirstAdminAccount();      // Creating admin account
    this.createSecondAdminAccount();      // Creating admin account

    this.createFirstModeratorAccount();
    this.createSecondModeratorAccount();

    this.createFirstSuperviserAccount();
    this.createSecondSuperviserAccount();

    this.createFirstInternAccount();
    this.createSecondInternAccount();
  }

  private void createDefaultCollege() {
    if (collegeRepository.existsByName("Uczelnia administracyjna")) {
      return;
    }
    College defaultCollege = new College();
    defaultCollege.setName("Uczelnia administracyjna");
    defaultCollege.setActive(true);
    defaultCollege.setDeleted(false);
    collegeRepository.save(defaultCollege);
  }

  private void createSuperAdminAccount() {
    if (userRepository.existsByEmail("admin@sop.pl")) {
      return;
    }
    User user = new User();
    user.setFirstName("Admin");
    user.setLastName("Admin");
    user.setUsername("admin");
    user.setEmail("admin@sop.pl");
    user.setPassword(decodePassword("password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role superAdminRole = roleRepository.findByName(ERole.ROLE_SUPERADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(superAdminRole);

    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(adminRole);

    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createFirstAdminAccount() {
    if (userRepository.existsByEmail("collegeAdmin@sop.pl")) {
      return;
    }
    User user = new User();
    user.setUsername("collegeAdmin");
    user.setEmail("collegeAdmin@sop.pl");
    user.setPassword(decodePassword( "password"));
    user.setFirstName("Adam");
    user.setLastName("Novik");
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(adminRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createSecondAdminAccount() {
    if (userRepository.existsByEmail("collegeAdmin2@sop.pl")) {
      return;
    }
    User user = new User();
    user.setUsername("collegeAdmin2");
    user.setEmail("collegeAdmin2@sop.pl");
    user.setPassword(decodePassword( "password"));
    user.setFirstName("Marcin");
    user.setLastName("Urban");
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(adminRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createFirstModeratorAccount() {
    if (userRepository.existsByEmail("moderator@sop.pl")) {
      return;
    }
    User user = new User();
    user.setFirstName("Małgorzata");
    user.setLastName("Woźniak");
    user.setUsername("moderator");
    user.setEmail("moderator@sop.pl");
    user.setPassword(decodePassword("password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role studentRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(studentRole);
    Role userRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createSecondModeratorAccount() {
    if (userRepository.existsByEmail("moderator2@sop.pl")) {
      return;
    }
    User user = new User();
    user.setFirstName("Michał");
    user.setLastName("Kowalski");
    user.setUsername("moderator2");
    user.setEmail("moderator2@sop.pl");
    user.setPassword(decodePassword("password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role studentRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(studentRole);
    Role userRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createFirstSuperviserAccount() {
    if (userRepository.existsByEmail("superviser@sop.pl")) {
      return;
    }
    User user = new User();
    user.setFirstName("Jan");
    user.setLastName("Przykładny");
    user.setUsername("superviser");
    user.setEmail("superviser@sop.pl");
    user.setPassword(decodePassword( "password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role superviserRole = roleRepository.findByName(ERole.ROLE_SUPERVISER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(superviserRole);

    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createSecondSuperviserAccount() {
    if (userRepository.existsByEmail("superviser2@sop.pl")) {
      return;
    }
    User user = new User();
    user.setFirstName("Wojciech");
    user.setLastName("Adamski");
    user.setUsername("superviser2");
    user.setEmail("superviser2@sop.pl");
    user.setPassword(decodePassword( "password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role superviserRole = roleRepository.findByName(ERole.ROLE_SUPERVISER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(superviserRole);

    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createFirstInternAccount() {
    if (userRepository.existsByEmail("intern@sop.pl")) {
      return;
    }
    User user = new User();
    user.setEmail("intern@sop.pl");
    user.setUsername("intern");
    user.setFirstName("Mateusz");
    user.setLastName("Walczak");
    user.setPassword(decodePassword("password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(studentRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private void createSecondInternAccount() {
    if (userRepository.existsByEmail("intern2@sop.pl")) {
      return;
    }
    User user = new User();
    user.setEmail("intern2@sop.pl");
    user.setUsername("intern2");
    user.setFirstName("Kaja");
    user.setLastName("Mila");
    user.setPassword(decodePassword("password"));
    user.setActive(true);
    user.setDeleted(false);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(userRole);
    Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
    roles.add(studentRole);
    if (roles != null) {
      user.setRoles(roles);
    }

    College college = collegeRepository.findCollegeByName("Uczelnia administracyjna");
    if (college == null) {
      return;
    }
    List<College> colleges = new ArrayList<>();
    colleges.add(college);
    user.setColleges(colleges);
    user.setSelectedCollegeId(college.getId());
    userRepository.save(user);
  }

  private String decodePassword(String plainPassword) {
    return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
  }
}
