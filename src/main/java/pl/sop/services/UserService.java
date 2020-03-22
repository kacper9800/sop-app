package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sop.dao.entitiy.User;
import pl.sop.dao.repository.UserRepository;

import java.util.List;

public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findUserById(id);

    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }
}

