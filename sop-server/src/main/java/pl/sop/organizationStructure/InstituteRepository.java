package pl.sop.organizationStructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

  @Query(value = "select distinct institute from Institute institute where institute.id = :id")
  public Optional<Institute> findById(@Param("id")Long instituteId);
}
