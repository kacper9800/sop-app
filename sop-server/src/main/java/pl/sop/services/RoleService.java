package pl.sop.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.entities.Role;
import pl.sop.enums.ERole;
import pl.sop.repositories.RoleRepository;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public ResponseEntity findAllRoles() {
    List<Role> roles = roleRepository.findAll();
    if (roles.size() == 0) {
      return ResponseEntity.notFound().build();
    }

    List<String> roleList = new ArrayList<>();
    for (Role role : roles) {
      if (role.getName().equals(ERole.ROLE_USER) || role.getName().equals(ERole.ROLE_SUPERADMIN)) {

      } else {
        roleList.add(role.getName().toString());
      }
    }
    return ResponseEntity.ok(roleList);
  }
}
