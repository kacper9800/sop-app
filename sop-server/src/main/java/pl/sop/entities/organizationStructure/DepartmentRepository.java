package pl.sop.entities.organizationStructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  @Query(value = "select department from Department department where"
      + " department.id = :department_id and"
      + " (department.active = true or department.active = null) and"
      + " (department.deleted = false or department.deleted = null)")
  public Optional<Department> findById(@Param("department_id") Long department_id);
}
