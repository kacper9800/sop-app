package pl.sop.organizationStructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

  @Query(value = "Select faculty from Faculty faculty where faculty.id = :id")
  public Optional<Faculty> findById(@Param("id") Long facultyId);
}
