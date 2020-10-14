package pl.sop.controllers;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.dto.CollegeDTO;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.dto.CollegeStructureDTO;
import pl.sop.dto.CollegeStructureToSaveDTO;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.CollegeService;
import pl.sop.security.services.UserDetailsImpl;

@RestController
@CrossOrigin
public class CollegeController {

  @Autowired
  private CollegeService collegeService;

  @GetMapping(value = "/api/colleges")
  public ResponseEntity<List<College>> getAllColleges() {
    final List<College> colleges = collegeService.findAllColleges();
    return new ResponseEntity<>(colleges, HttpStatus.OK);
  }

  @PostMapping(value = "/api/colleges/createNew")
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  public ResponseEntity createNewCollege(@RequestBody CollegeDTO collegeDTO) {
    this.collegeService.createNewCollege(collegeDTO);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PostMapping(value = "/api/colleges/activate")
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  public ResponseEntity activateCollege(@RequestBody ActivationKeyDTO activationKeyDTO) {
    this.collegeService.activateCollege(activationKeyDTO);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PostMapping(value = "/api/colleges/register")
  @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
  public ResponseEntity registerCollege(@RequestBody CollegeRegistrationDTO collegeRegistrationDTO) {
    this.collegeService.registerCollege(collegeRegistrationDTO);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping(value = "/api/available-colleges")
  public ResponseEntity<List<CollegeDTO>> getAllAvailableColleges() {
    final List<CollegeDTO> colleges = collegeService.findAllAvailableColleges();
    return new ResponseEntity<>(colleges, HttpStatus.OK);
  }

  @GetMapping(value = "/api/college-structure")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<CollegeStructureDTO> getCollegeStructure(Authentication authentication) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    if (user.getSelectedCollegeId() == null) {
      return new ResponseEntity("College not found!", HttpStatus.CONFLICT);
    }
    CollegeStructureDTO collegeStructureDTO = collegeService
        .findAllCollegeStructures(user.getSelectedCollegeId());
    return new ResponseEntity<>(collegeStructureDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/college-structure")
  public ResponseEntity<CollegeStructureToSaveDTO> createNewCollegeStructure(Authentication authentication,
      @RequestBody CollegeStructureToSaveDTO collegeStructureToSaveDTO) {
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
    return collegeService.createNewCollegeStructure(collegeStructureToSaveDTO);
  }

  @DeleteMapping(value = "/api/college-structure")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
  public ResponseEntity<Long> deleteCollegeStructure(@RequestParam Long collegeStructureId,
      @RequestParam String collegeStructure) {
    collegeService.deleteCollegeStructure(collegeStructureId, collegeStructure);
    return ResponseEntity.ok(collegeStructureId);
  }

//    College college = user.getCollege();
//    if (college == null) {
//      return new ResponseEntity("College not found!",HttpStatus.CONFLICT);
//    }
//    CollegeStructureDTO collegeStructureDTO = collegeService.findAllCollegeStructures(college.getId());
//    return new ResponseEntity<>(collegeStructureDTO,HttpStatus.OK);

//  @GetMapping(value = "/api/college/{id}")
//  public ResponseEntity<List<College>> getAllCollegesByVoivoId(@PathVariable Long id) {
//    final List<College> colleges = collegeService.findAllCollegesByVoivodeship_id(id);
//    return new ResponseEntity<>(colleges, HttpStatus.OK);
//  }

//  @PostMapping(value = "/api/college/")
//  public ResponseEntity<?> saveNewCollege(@RequestBody CollegeRegistrationDTO collegeRegistrationDTO) {
//    collegeService.save(collegeRegistrationDTO);
//    return ResponseEntity.ok(HttpStatus.OK);
//  }

  @PutMapping(value = "/api/college/{id}")
  public ResponseEntity<?> updateCollege(@PathVariable("id") Long id,
      @RequestBody College college) {
    collegeService.update(id, college);
    return ResponseEntity.ok(HttpStatus.OK);
  }


}
