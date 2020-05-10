package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dao.entities.User;
import pl.sop.dao.repository.UserRepository;

import java.util.List;

@Service
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
