package pl.sop.controllers;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sop.dao.entitiy.User;
import pl.sop.dao.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userRepository.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK)   ;

    }
}

