package pl.sop.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dao.entities.organizationStructure.College;
import pl.sop.dao.repository.CollegeRepository;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;

@Service
public class CollegeService {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private UserService userService;


  public List<College> findAllColleges() {
    return collegeRepository.findAll();
  }

  public List<College> findAllAvailableColleges() {
    return collegeRepository.finAllAvailableColleges();
  }

  public List<College> findAllCollegesByVoivodeship_id(Long id) {
    return collegeRepository.findAllCollegesByVoivodeship_id(id);
  }

  public void save(CollegeRegistrationDTO collegeRegistrationDTO) {
    College college = collegeRepository.findCollegeById(collegeRegistrationDTO.getCollegeId());
    college.setActive(Boolean.TRUE);

    userService.registerUser(createCollegeAdmin(collegeRegistrationDTO));

    collegeRepository.updateCollege();
  }

  public SignUpRequest createCollegeAdmin(CollegeRegistrationDTO collegeRegistrationDTO) {
    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setUsername(collegeRegistrationDTO.getEmail());
    signUpRequest.setEmail(collegeRegistrationDTO.getEmail());
    signUpRequest.setPassword(collegeRegistrationDTO.getPassword());
    Set<String> roles = new HashSet<>();
    roles.add(ERole.ROLE_USER.toString());
    roles.add(ERole.ROLE_ADMIN.toString());
    roles.add(ERole.ROLE_SUPERADMIN.toString());
    signUpRequest.setRole(roles);
    return signUpRequest;
  }

  public void updateCollege(Long id, College college) {
    collegeRepository.updateCollege();
  }
}
