/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.controllers.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.CollegeDTO;
import pl.sop.repositories.RoleRepository;
import pl.sop.repositories.UserRepository;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.payload.request.LoginRequest;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.JwtResponse;
import pl.sop.security.jwt.JwtUtils;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.entities.organizationStructure.CollegeService;
import pl.sop.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserService userService;

  @Autowired
  private CollegeService collegeService;

  @PostMapping("/signIn")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
              loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
          .collect(Collectors.toList());
      List<CollegeDTO> colleges = new ArrayList<>();
      if (userDetails.getColleges().size() != 0) {
        userDetails.getColleges().forEach(college -> {
          CollegeDTO collegeDTO = new CollegeDTO(college.getId(), college.getName(),
              college.getActive(), college.getDeleted());
          colleges.add(collegeDTO);
        });
      }

      return ResponseEntity.ok(new JwtResponse(jwt,
          userDetails.getId(),
          userDetails.getUsername(),
          userDetails.getEmail(),
          roles,
          colleges,
          userDetails.getSelectedCollegeId()));
    } catch (BadCredentialsException exception) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/signUp")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    return userService.registerUser(signUpRequest, false);
  }

  @PostMapping("/signUpCollege")
  public ResponseEntity<?> registerCollege(@Valid @RequestBody CollegeRegistrationDTO collegeRegistrationDTO) {
    return collegeService.registerCollege(collegeRegistrationDTO);
  }

//  @PostMapping("/signUpCompany")
//  public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanyRegistartionDTO companyRegistartionDTO) {
//    return collegeService.registerCollege(collegeRegistrationDTO);
//  }
}
