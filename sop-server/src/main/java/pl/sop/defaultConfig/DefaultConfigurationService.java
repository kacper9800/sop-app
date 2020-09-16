package pl.sop.defaultConfig;

import java.util.ArrayList;
import java.util.HashSet;
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

    College defaultCollege = new College();
    defaultCollege.setName("Uczelnia administracyjna");
    defaultCollege.setActive(true);
    defaultCollege.setDeleted(false);
    collegeRepository.save(defaultCollege);

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

    user.setCollege(defaultCollege);
    userRepository.save(user);
  }

  private String decodePassword(String plainPassword) {
    return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
  }
}
