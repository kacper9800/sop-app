package pl.sop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.EventDTO;
import pl.sop.dto.TokenDTO;
import pl.sop.organizationStructure.College;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.ActivationKeyService;

@RestController
public class ActivationKeyController {

  @Autowired
  private ActivationKeyService activationKeyService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/activationKeys", method = RequestMethod.GET)
  public ResponseEntity<List<TokenDTO>> getAllActivationKeys(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Set<College> colleges = loggedUser.getCollege();
    if (colleges.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    List<College> collegeList = new ArrayList<>(colleges);
    return new ResponseEntity(activationKeyService.getAllTokensForCompany(collegeList.get(0).getId()), HttpStatus.OK);
  }

//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/planner/event/{id}", method = RequestMethod.GET)
//  public ResponseEntity<List<EventDTO>> getAllEventsForUserId(@PathVariable("id") Long id) {
//    final List<EventDTO> events = eventService.getAllEventsForUserId(id);
//    return new ResponseEntity(events, HttpStatus.OK);
//  }
//
//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/planner/event/base", method = RequestMethod.GET)
//  public ResponseEntity<List<EventDTO>> getAllBaseEvents() {
//    final List<EventDTO> events = eventService.getAllEventsWithoutDate();
//    return new ResponseEntity(events, HttpStatus.OK);
//  }
//
//
//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/planner/event", method = RequestMethod.POST)
//  public ResponseEntity createNewEvent(@RequestBody EventDTO eventDTO) throws ParseException {
//    return ResponseEntity.ok(eventService.createEvent(eventDTO));
//  }
//
//  @CrossOrigin
//  @PreAuthorize("hasRole('ROLE_MODERATOR')")
//  @RequestMapping(value = "/api/planner/event/{id}", method = RequestMethod.DELETE)
//  public ResponseEntity.BodyBuilder deleteEvent(@PathVariable("id") Long id) throws Exception {
//    if (id == null) {
//      throw new Exception("Provided id is wrong!");
//    }
//    eventService.deleteEvent(id);
//    return ResponseEntity.status(HttpStatus.OK);
//  }
}
