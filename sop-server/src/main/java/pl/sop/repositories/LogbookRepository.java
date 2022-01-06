package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Logbook;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, Long> {

  @Query(value = "SELECT distinct logbook from Logbook logbook "
      + " left join logbook.logbookPosts logbookPosts"
      + " left join logbook.intern intern "
      + " left join logbook.internship internship"
      + " left join internship.request request"
      + " left join request.company company"
      + " left join logbook.college college "
      + " where college.id = :collegeId and intern.id = :internId")
  List<Logbook> findAllLogbooksForInternIdAndCollegeId(@Param("internId") Long internId,
      @Param("collegeId") Long collegeId);

  @Query(value = "SELECT logbook from Logbook logbook "
      + " left join logbook.logbookPosts logbookPosts"
      + " left join logbook.intern intern"
      + " where logbook.id = :internId")
  Logbook findLogbookById(@Param("internId") Long internId);

  @Query(value = "SELECT logbook from Logbook logbook "
      + " left join logbook.institute institute "
      + " where institute.id = :instituteId")
  List<Logbook> findAllLogbooksForInstituteId(@Param("instituteId") Long instituteId);
}
