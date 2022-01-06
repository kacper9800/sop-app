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
import pl.sop.dto.InternDTO;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.DictionaryService;
import pl.sop.services.InternService;

@Controller
public class InternController {

  @Autowired
  private InternService internService;

//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/interns-college", method = RequestMethod.GET)
//  public ResponseEntity<List<InternDTO>> getAllInternsForCollege(Authentication authentication) {
//    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
//    Long collegeId = loggedUser.getSelectedCollegeId();
//    if (collegeId == null) {
//      return ResponseEntity.notFound().build();
//    }
//    return this.internService.getAllInternsForCollege(collegeId);
//  }
//
//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/interns-institute", method = RequestMethod.GET)
//  public ResponseEntity<List<DictionaryDTO>> getAllInternsForInstitute(Authentication authentication) {
//    return this.internService.getAllInternsForInstitute(instituteId);
//  }
//
//  @CrossOrigin
//  @RequestMapping(value = "/api/dictionaries/request-decision-statuses", method = RequestMethod.GET)
//  public ResponseEntity<List<DictionaryDTO>> getAllRequestDecisionStatuses(Authentication authentication) {
//    return this.dictionaryService.getAllRequestDecisionStatuses();
//  }
//
//  @CrossOrigin
////  @PreAuthorize("hasRole('ROLE_STUDENT')")
//  @RequestMapping(value = "/api/dictionaries/request-types", method = RequestMethod.GET)
//  public ResponseEntity<List<DictionaryDTO>> getAllRequestTypes(Authentication authentication) {
//    return this.dictionaryService.getAllRequestTypes();
//  }



}
