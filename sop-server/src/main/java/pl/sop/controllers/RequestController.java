package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sop.dto.RequestDTO;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.RequestService;

@Controller
public class RequestController {

  @Autowired
  private RequestService requestService;

  // Requests - wnioski o praktyki
  // Endpointy:
  //  > /api/requests - getAllRequests GET
  //  > /api/requests/{id} - getRequestById GET
  //  > /api/requests - createNewRequest POST
  //  > /api/requests - updateRequest PUT
  //  > /api/requests/{id} - deleteRequest DELETE

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/requests/student", method = RequestMethod.GET)
  public ResponseEntity<List<RequestDTO>> getAllRequestsForStudent(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.requestService.getAllRequestsForStudent(loggedUser.getId());
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/requests/institute", method = RequestMethod.GET)
  public ResponseEntity<List<RequestDTO>> getAllRequestsForInstitute(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.requestService.getAllRequestsForInstitute(loggedUser.getId());
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_DIRECTOR')")
  @RequestMapping(value = "/api/requests/director", method = RequestMethod.GET)
  public ResponseEntity<List<RequestDTO>> getAllRequestsForDirector(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.requestService.getAllRequestsForDirectorId(loggedUser.getId());
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/requests/college", method = RequestMethod.GET)
  public ResponseEntity<List<RequestDTO>> getAllRequestsForCollege(Authentication authentication) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    Long collegeId = loggedUser.getSelectedCollegeId();
    if (collegeId == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.requestService.getAllRequestsForCollege(loggedUser.getSelectedCollegeId());
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_STUDENT')")
  @RequestMapping(value = "/api/requests/{id}", method = RequestMethod.GET)
  public ResponseEntity<RequestDTO> getRequestById(Authentication authentication,
      @PathVariable("id") Long id) {
    if (id == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    return this.requestService.getRequestById(id);
  }

  @CrossOrigin
  @RequestMapping(value = "/api/requests", method = RequestMethod.POST)
  public ResponseEntity createRequest(Authentication authentication,
      @RequestBody RequestDTO requestDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    if (requestDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    requestDTO.setCollegeId(loggedUser.getSelectedCollegeId());
    requestDTO.setInternId(loggedUser.getId());
    return this.requestService.createRequest(requestDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/requests/moderatorResponse", method = RequestMethod.PUT)
  public ResponseEntity responseRequestAsModerator(Authentication authentication,
      @RequestBody RequestDTO requestDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    if (requestDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    requestDTO.setCollegeId(loggedUser.getSelectedCollegeId());
    requestDTO.setInternId(loggedUser.getId());
    return this.requestService.updateAsModerator(requestDTO);
  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_DIRECTOR')")
  @RequestMapping(value = "/api/requests/directorResponse", method = RequestMethod.PUT)
  public ResponseEntity responseRequestAsAdmin(Authentication authentication,
      @RequestBody RequestDTO requestDTO) {
    UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
    if (requestDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    requestDTO.setCollegeId(loggedUser.getSelectedCollegeId());
    requestDTO.setInternId(loggedUser.getId());
    return this.requestService.updateAsAdmin(requestDTO);
  }

//
//  @CrossOrigin
//  @RequestMapping(value = "/api/requests", method = RequestMethod.PUT)
//  public ResponseEntity updateRequest(Authentication authentication, @RequestBody()) {
//
//  }

  @CrossOrigin
  @PreAuthorize("hasRole('ROLE_MODERATOR')")
  @RequestMapping(value = "/api/requests/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteRequest(Authentication authentication, @PathVariable("id") Long id) {
    return this.requestService.deleteRequest(id);
  }
}
