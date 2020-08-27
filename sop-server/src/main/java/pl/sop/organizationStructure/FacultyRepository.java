package pl.sop.organizationStructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

  @Query(value = "select faculty from Faculty faculty where"
      + " faculty.id = :faculty_id and"
      + " (faculty.active = true or faculty.active = null) and"
      + " (faculty.deleted = false or faculty.deleted = null)")
  public Optional<Faculty> findById(@Param("faculty_id") Long facultyId);
}
