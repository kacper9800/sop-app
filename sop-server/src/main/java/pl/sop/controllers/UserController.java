package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sop.entities.User;
import pl.sop.repositories.UserRepository;
import pl.sop.security.services.UserDetailsImpl;
import pl.sop.services.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping(value = "/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/api/tests")
    public String getTest() {
        return "ok";
    }

    @GetMapping(value = "/api/users/changeCollege/{id}")
    public ResponseEntity changeCollege(Authentication authentication, @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return userService.changeCollege(user.getId(), id);
    }
}
