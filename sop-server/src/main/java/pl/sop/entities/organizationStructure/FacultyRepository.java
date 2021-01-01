package pl.sop.entities.organizationStructure;

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

  @Query(value = "select faculty from Faculty faculty"
      + " where faculty.id = :faculty_id"
      + " and faculty.active = true"
      + " and faculty.deleted = false")
  Faculty findActiveFacultyById(@Param("faculty_id") Long facultyId);

  @Query(value = "select faculty from Faculty faculty"
      + " left join fetch faculty.institutes institutes"
      + " left join fetch institutes.departments departments"
      + " where faculty.id = :faculty_id"
      + " and faculty.active = true"
      + " and faculty.deleted = false")
  Faculty findActiveFacultyStructureById(@Param("faculty_id") Long facultyId);
}
