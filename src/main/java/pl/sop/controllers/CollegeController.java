package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sop.dao.entitiy.College;
import pl.sop.dao.repository.CollegeRepository;

import java.util.List;

@RestController
public class CollegeController {
    @Autowired
    CollegeRepository collegeRepository;

    public CollegeController(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @GetMapping(value = "/api/college")
    public ResponseEntity<List<College>> getAllColleges() {
        final List<College> colleges = collegeRepository.findAllColleges();
        return new ResponseEntity<>(colleges, HttpStatus.OK);
    }

    @GetMapping(value = "/api/college/{id}")
    public ResponseEntity<List<College>> getAllCollegesByVoivoId(@PathVariable Long id) {
        final List<College> colleges = collegeRepository.findAllCollegesByVoivodeship_id(id);
        return new ResponseEntity<>(colleges, HttpStatus.OK);
    }

    @PostMapping(value = "/api/college/")
    public HttpStatus saveNewCollege(@RequestBody College college) {
        //ToDo
        collegeRepository.save(college);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/api/college/{id}")
    public HttpStatus updateCollege(@RequestBody College college) {
        //ToDo
        collegeRepository.save(college);
        return HttpStatus.OK;
    }

}
