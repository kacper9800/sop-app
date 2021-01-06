package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  @Query(value = "SELECT request from Request request "
      + " left join request.intern intern "
      + " where intern.id = :internId")
  List<Request> getAllRequestsForStudent(@Param("internId") Long internId);

  @Query(value = "SELECT request from Request request "
      + " left join request.institute institute"
      + " where institute.id in :institutesId")
  List<Request> getAllRequestsForInstitutes(@Param("institutesId") List<Long> institutesId);

  @Query(value = "select request from Request request "
      + "left join request.admin admin where admin.id = :directorId")
  List<Request> getAllRequestsForDirectorId(@Param("directorId") Long directorId);

  @Query(value = "SELECT request from Request request "
      + " left join request.intern intern "
      + " left join request.institute institute"
      + " left join institute.faculty faculty"
      + " left join faculty.college college"
      + " where college.id = :collegeId")
  List<Request> getAllRequestsForCollege(@Param("collegeId") Long collegeId);

  @Query(value = "SELECT request from Request request "
      + " left join request.intern intern "
      + " left join request.institute institute "
      + " left join institute.faculty faculty "
      + " left join faculty.college college "
      + " where request.id = :id")
  Request getRequestById(@Param("id") Long id);


}
