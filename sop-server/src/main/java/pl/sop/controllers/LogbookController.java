package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sop.dto.LogbookDTO;
import pl.sop.dto.LogbookPostDTO;
import pl.sop.entities.Logbook;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.LogbookService;

@Controller
public class LogbookController {
  // Endpointy:
  // getAllLogbooks
  // getLogbookById
  // getLogbookForInternId
  @Autowired
  private LogbookService logbookService;


  // ToDo
  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/logbooks", method = RequestMethod.GET)
  public ResponseEntity<List<LogbookDTO>> getAllLogbooks(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.getAllLogbooks(null);
  }

  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/logbooks/{id}", method = RequestMethod.GET)
  public ResponseEntity<LogbookDTO> getLogbookById(@PathVariable("id") Long id,
      Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.getLogbookById(id);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/logbooks/intern/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<Logbook>> getAllLogbooksForInternId(Authentication authentication, @PathVariable("id") Long id) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (id == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.getAllLogbooksForInternIdAndCollegeId(id, collegeId);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/logbooks/intern", method = RequestMethod.GET)
  public ResponseEntity<List<Logbook>> getAllLogbooksForLoggedIntern(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long internId = loggedUser.getId();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (internId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.getAllLogbooksForInternIdAndCollegeId(internId, collegeId);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/logbooks-posts/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<LogbookPostDTO>> getLogbookPostsByLogbookId(
      @PathVariable("id") Long id, Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.getLogbookPostsByLogbookId(id);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/logbooks", method = RequestMethod.POST)
  public ResponseEntity<?> createLogbook(Authentication authentication, @RequestBody LogbookDTO logbookDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long internId = loggedUser.getId();
    Long collegeId = loggedUser.getSelectedCollegeId();
    logbookDTO.setCollegeId(collegeId);
    logbookDTO.setInternId(internId);
    if (internId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.createNewLogbook(logbookDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/logbooks-posts", method = RequestMethod.POST)
  public ResponseEntity<?> createLogbookPost(Authentication authentication, @RequestBody LogbookPostDTO logbookPostDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long internId = loggedUser.getId();
    if (logbookPostDTO != null && logbookPostDTO.getLogbookId() == null) {
      return ResponseEntity.badRequest().build();
    }
    if (internId == null) {
      return ResponseEntity.noContent().build();
    }
    return this.logbookService.createNewLogbookPost(logbookPostDTO);
  }
}
