package pl.sop.organizationStructure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.services.ActivationKeyService;
import pl.sop.services.UserService;

@Service
public class CollegeService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ActivationKeyService activationKeyService;


  public List<College> findAllColleges() {
    return collegeRepository.findAllColleges();
  }

  public List<College> findAllAvailableColleges() {
    return collegeRepository.findAllAvailableColleges();
  }

  public ResponseEntity<?> registerCollege(CollegeRegistrationDTO collegeRegistrationDTO) {
    if(!activationKeyService.isValidTokenForCollege(collegeRegistrationDTO.getCollegeId(), collegeRegistrationDTO.getToken())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Provided access token is wrong!"));
    }

    College college = collegeRepository.findCollegeById(collegeRegistrationDTO.getCollegeId());
    college.setActive(Boolean.TRUE);
    userService.registerUser(createCollegeAdmin(collegeRegistrationDTO), true);
    return ResponseEntity.ok(collegeRepository.save(college));
  }


  public SignUpRequest createCollegeAdmin(CollegeRegistrationDTO collegeRegistrationDTO) {
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setUsername(collegeRegistrationDTO.getEmail());
    signUpRequest.setEmail(collegeRegistrationDTO.getEmail());
    signUpRequest.setPassword(collegeRegistrationDTO.getPassword());
    signUpRequest.setCollegeId(collegeRegistrationDTO.getCollegeId());
    signUpRequest.setToken(collegeRegistrationDTO.getToken());
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_USER.toString());
    roles.add(ERole.ROLE_ADMIN.toString());
    signUpRequest.setRole(roles);
    return signUpRequest;
  }


  public void update(Long id, College college) {
    //ToDo
  }
}
