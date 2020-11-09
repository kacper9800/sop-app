package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sop.services.RoleService;

@Controller
public class RoleController {

  @Autowired
  private RoleService roleService;

  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_SUPERADMIN')")
  @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
  public ResponseEntity getAllRoles() {
    return roleService.findAllRoles();
  }


}
