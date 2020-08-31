package pl.sop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.sop.entities.User;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.deleted = FALSE AND u.active = TRUE")
    List<User> findAllUsers();

    @Query(value = "SELECT u FROM User  u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);

    @Query(value = "select u from User u left join fetch u.colleges c where u.username = :username and u.active = true")
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
