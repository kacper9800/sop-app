package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  @Query(value = "SELECT request from Request request where request.intern.id = :internId and request.college.id = :collegeId")
  List<Request> getAllRequests(@Param("internId") Long internId, @Param("collegeId") Long collegeId);

}
