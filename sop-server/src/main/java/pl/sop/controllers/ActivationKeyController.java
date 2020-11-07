package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.organizationStructure.College;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.ActivationKeyService;

@RestController
public class ActivationKeyController {

  @Autowired
  private ActivationKeyService activationKeyService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  @RequestMapping(value = "/api/activationKeys/value", method = RequestMethod.GET)
  public ResponseEntity<ActivationKeyDTO> getActivationKeyForValue(Authentication authentication,
      @RequestParam("keyValue") String activationKeyValue) {
    return this.activationKeyService.getActivationKeyByValue(activationKeyValue);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/activationKeys", method = RequestMethod.GET)
  public ResponseEntity<List<ActivationKeyDTO>> getAllActivationKeys(
      Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    College college = loggedUser.getColleges().stream()
        .filter(col -> col.getId().equals(loggedUser.getSelectedCollegeId())).findFirst().get();
    if (college == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity(activationKeyService.getAllTokensForCollege(college.getId()), HttpStatus.OK);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  // Only superadmin can create activation key for college
  @RequestMapping(value = "/api/activationKeys/college", method = RequestMethod.POST)
  public ResponseEntity<ActivationKey> createActivationKeyForCollege(Authentication authentication,
      @RequestBody ActivationKeyDTO activationKeyDTO) {
    if (activationKeyDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    return activationKeyService.createNewActivationKeyForCollege(activationKeyDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  // Only superadmin can create activation key for company
  @RequestMapping(value = "/api/activationKeys/company", method = RequestMethod.POST)
  public ResponseEntity<ActivationKey> createActivationKeyForCompany(Authentication authentication,
      @RequestBody ActivationKeyDTO activationKeyDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
//    return activationKeyService.createNewActivationKeyForCompany(tokenDTO); ToDo
    return null;
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
  @RequestMapping(value = "/api/activationKeys/college-structure", method = RequestMethod.POST)
  public ResponseEntity<ActivationKey> createActivationKey(@RequestBody ActivationKeyDTO activationKeyDTO) {
    if (activationKeyDTO == null) {
      return ResponseEntity.badRequest().build();
    }
    return activationKeyService.createNewActivationKey(activationKeyDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
  @RequestMapping(value = "/api/activationKeys", method = RequestMethod.PUT)
  public ResponseEntity<ActivationKeyDTO> updateActivationKey(@RequestBody ActivationKeyDTO activationKeyDTO) {
    return activationKeyService.updateActivationKey(activationKeyDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
  @RequestMapping(value = "/api/activationKeys/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> deleteActivationKey(@PathVariable(value = "id") Long id) {
    return activationKeyService.deleteActivationKey(id);
  }
}
