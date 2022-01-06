package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sop.dto.InstituteDTO;
import pl.sop.entities.organizationStructure.InstituteService;
import pl.sop.security.services.UserDetailsImpl;

@Controller
public class InstituteController {

  @Autowired
  private InstituteService instituteService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_DIRECTOR')")
  @RequestMapping(value = "/api/college-institutes", method = RequestMethod.GET)
  public ResponseEntity<List<InstituteDTO>> getAllInstitutes(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.notFound().build();
    }
    return this.instituteService.getAllInstitutesForCollege(collegeId);
  }
}
