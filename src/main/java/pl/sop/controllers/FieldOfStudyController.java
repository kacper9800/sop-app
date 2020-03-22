package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sop.dao.entitiy.FieldOfStudy;
import pl.sop.dao.repository.FieldOfStudyRepository;

@RestController
public class FieldOfStudyController {
    @Autowired
    FieldOfStudyRepository fieldOfStudyRepository;


//    @GetMapping(value = "/api/college")
//    public ResponseEntity<List<College>> getAllColleges() {
//        final List<College> colleges = fieldOfStudyRepository.fin();
//        return new ResponseEntity<>(colleges, HttpStatus.OK);
//    }

    @PostMapping(value = "/api/field/")
    public HttpStatus saveNewCollege(@RequestBody FieldOfStudy fieldOfStudy) {
        //ToDo
        fieldOfStudyRepository.save(fieldOfStudy);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/api/filed/{id}")
    public HttpStatus updateCollege(@PathVariable Long id, @RequestBody FieldOfStudy fieldOfStudy) {
        //ToDo
        fieldOfStudyRepository.save(fieldOfStudy);
        return HttpStatus.OK;
    }
}


