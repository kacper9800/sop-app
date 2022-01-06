package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Internship;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {

//  @Query(value = "select ")
//  List<Internship> getAllInternsForCollege(@Param("collegeId") Long collegeId)



}
