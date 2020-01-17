package pl.sop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @RequestMapping("/users")
    public String getAllUsers() {
        String user = "user";
        return user;
    }
}
