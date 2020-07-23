package pl.sop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dao.entities.organizationStructure.College;
import pl.sop.dao.repository.CollegeRepository;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.services.CollegeService;

@RestController
public class CollegeController {

  @Autowired
  CollegeService collegeService;

  @Autowired
  CollegeRepository collegeRepository;

  @GetMapping(value = "/api/college")
  public ResponseEntity<List<College>> getAllColleges() {
    final List<College> colleges = collegeService.findAllColleges();
    return new ResponseEntity<>(colleges, HttpStatus.OK);
  }

  @GetMapping(value = "/api/available-colleges")
  public ResponseEntity<List<College>> getAllAvailableColleges() {
    final List<College> colleges = collegeService.findAllAvailableColleges();
    return new ResponseEntity<>(colleges, HttpStatus.OK);
  }

  @GetMapping(value = "/api/college/{id}")
  public ResponseEntity<List<College>> getAllCollegesByVoivoId(@PathVariable Long id) {
    final List<College> colleges = collegeService.findAllCollegesByVoivodeship_id(id);
    return new ResponseEntity<>(colleges, HttpStatus.OK);
  }

  @PostMapping(value = "/api/college/")
  public HttpStatus saveNewCollege(@RequestBody CollegeRegistrationDTO collegeRegistrationDTO) {
    collegeService.save(collegeRegistrationDTO);
    return HttpStatus.OK;
  }

  @PutMapping(value = "/api/college/{id}")
  public HttpStatus updateCollege(@PathVariable("id") Long id, @RequestBody College college) {
    //ToDo
    collegeService.updateCollege(id,college);
    return HttpStatus.OK;
  }

}
