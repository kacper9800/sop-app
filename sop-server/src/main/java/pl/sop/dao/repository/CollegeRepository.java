package pl.sop.dao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entities.organizationStructure.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

  @Query(value = "SELECT c FROM College c")
  List<College> findAllColleges();

  @Query(value = "SELECT c FROM College c WHERE c.voivodeship_id = :voivo_id")
  List<College> findAllCollegesByVoivodeship_id(@Param("voivo_id") Long voivo_id);

  @Query(value = "SELECT c FROM College c WHERE c.active = false")
  List<College> finAllAvailableColleges();

  @Query(value = "SELECT c FROM College c WHERE c.id = :college_id and c.deleted = false ")
  College findCollegeById(@Param("college_id") Long collegeId);

  //Todo
  @Query(value = "SELECT c FROM College c")
  List<College> updateCollege();
}
