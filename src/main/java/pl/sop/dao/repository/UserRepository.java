package pl.sop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sop.dao.entitiy.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.deleted = FALSE ")
    List<User> findAllUsers();

    @Query(value = "SELECT u FROM User  u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);

}
