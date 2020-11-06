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
import pl.sop.dto.DictionaryDTO;
import pl.sop.dto.InstituteDTO;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.DictionaryService;

@Controller
public class DictionaryController {

  @Autowired
  private DictionaryService dictionaryService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/dictionaries/study-modes", method = RequestMethod.GET)
  public ResponseEntity<List<DictionaryDTO>> getAllStudyModes(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.notFound().build();
    }
    return this.dictionaryService.getAllStudyModes();
  }

  @CrossOrigin
  @RequestMapping(value = "/api/dictionaries/sex-types", method = RequestMethod.GET)
  public ResponseEntity<List<DictionaryDTO>> getAllSexTypes(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.notFound().build();
    }
    return this.dictionaryService.getAllSexTypes();
  }

}
