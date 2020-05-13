package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sop.dao.entities.User;
import pl.sop.dao.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Dao<User> {

    @Autowired
    public UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findUserById(id);

    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllUsers();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);

    }

    @Override
    public void update(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
