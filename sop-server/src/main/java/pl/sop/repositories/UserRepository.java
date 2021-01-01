package pl.sop.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.sop.entities.User;

@Repository
@CrossOrigin(origins = "*")
public interface UserRepository extends JpaRepository<User, Long> {

  @Override
  <S extends User> S save(S entity);

  @Query(value = "SELECT u FROM User u WHERE u.deleted = FALSE AND u.active = TRUE")
  List<User> findAllUsers();

  @Query(value = "SELECT u FROM User  u WHERE u.id = :id")
  User findUserById(@Param("id") Long id);

  @Query(value = "select u from User u "
      + " left join fetch u.colleges colleges"
      + " left join fetch u.faculties faculties"
      + " left join fetch u.institutes institutes "
      + " left join fetch u.departments departments "
//        + " left join fetch u.companies companies "
      + " where u.username = :username and u.active = true")
  Optional<User> findByUsername(@Param("username") String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "select u from User u "
      + "left join fetch u.colleges colleges "
      + "where colleges.id = :collegeId")
  List<User> findAllUsersForCollegeId(@Param("collegeId") Long collegeId);

  @Query(value = "SELECT u.username from User u where u.username like :newUserName%")
  List<String> findSameUserNames(@Param("newUserName")String newUserName);

  @Query(value = "SELECT u FROM User u ")
  List<User> findModeratorByInstituteId(Long id);
}
