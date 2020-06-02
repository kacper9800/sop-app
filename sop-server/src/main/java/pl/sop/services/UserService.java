package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dao.entities.User;
import pl.sop.dao.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAllUsers();
        Comparator<User> comparator = (User u1, User u2) -> u1.getId().compareTo(u2.getId());
        Collections.sort(users, comparator);
        return users;
    }

}
