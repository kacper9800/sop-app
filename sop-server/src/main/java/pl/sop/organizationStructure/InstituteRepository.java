package pl.sop.organizationStructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

  @Query(value = "select institute from Institute institute where"
      + " institute.id = :institute_id and"
      + " (institute.active = true or institute.active = null) and"
      + " (institute.deleted = false or institute.deleted = null)")
  public Optional<Institute> findById(@Param("institute_id")Long instituteId);
}
