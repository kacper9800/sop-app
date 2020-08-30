package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.EventDTO;
import pl.sop.dto.TokenDTO;
import pl.sop.services.ActivationKeyService;

@RestController
public class ActivationKeyController {

  @Autowired
  private ActivationKeyService activationKeyService;

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/planner/event", method = RequestMethod.GET)
  public ResponseEntity<List<EventDTO>> getAllActivityKeys(Long collegeId) {
    return new ResponseEntity(activationKeyService.getAllTokensForCompany(collegeId), HttpStatus.OK);
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
