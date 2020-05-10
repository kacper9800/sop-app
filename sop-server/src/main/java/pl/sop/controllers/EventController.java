package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sop.dao.entities.User;
import pl.sop.dao.repository.UserRepository;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/event", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userRepository.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
