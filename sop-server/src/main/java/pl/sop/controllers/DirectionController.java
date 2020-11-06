package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.dto.DirectionDTO;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.ActivationKeyService;
import pl.sop.services.DirectionService;

@RestController
public class DirectionController {

  @Autowired
  private DirectionService directionService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/directions/{id}", method = RequestMethod.GET)
  public ResponseEntity<DirectionDTO> getDirectionById(Authentication authentication, @PathVariable("id") Long id) {
    if (id == null) {
      return ResponseEntity.notFound().build();
    }
    return this.directionService.getDirectionById(id);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/college-directions", method = RequestMethod.GET)
  public ResponseEntity<List<DirectionDTO>> getAllDirections(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.notFound().build();
    }
    return this.directionService.getAllDirectionForCollege(collegeId);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
  @RequestMapping(value = "/api/directions", method = RequestMethod.POST)
  public ResponseEntity<DirectionDTO> createDirection(Authentication authentication, @RequestBody DirectionDTO directionDTO) {
    if (directionDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.directionService.saveDirection(directionDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
  @RequestMapping(value = "/api/directions/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<DirectionDTO> deleteDirection(Authentication authentication, @PathVariable("id") Long id) {
    if (id == null) {
      return ResponseEntity.notFound().build();
    }
    return this.directionService.deleteDirection(id);
  }


}
