package pl.sop.organizationStructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

  @Query(value = "SELECT c FROM College c")
  List<College> findAllColleges();

//  @Query(value = "SELECT c FROM College c WHERE c.voivodeship_id = :voivo_id")
//  List<College> findAllCollegesByVoivodeship_id(@Param("voivo_id") Long voivo_id);

  @Query(value = "SELECT c FROM College c WHERE c.active = false")
  List<College> findAllAvailableColleges();

  @Query(value = "SELECT college from College college where college.id = :college_id")
  College findOnlyCollegeById(@Param("college_id") Long collegeId);

  @Query(value = "SELECT college from College college where college.name = :college_name")
  College findCollegeByName(@Param("college_name") String collegeName);

  @Query(value = "select college from College college"
      + " left join fetch college.faculties faculties"
      + " left join fetch faculties.institutes institutes"
      + " left join fetch institutes.departments"
      + " where college.id = :college_id")
  College findCollegeById(@Param("college_id") Long collegeId);

  @Query(value = "select college from College college"
      + " where college.id = :college_id"
      + " and college.active = true"
      + " and college.deleted = false")
  College findActiveCollegeById(@Param("college_id") Long collegeId);

  @Query(value = "select distinct college from College college"
      + " left join fetch college.faculties faculties"
      + " where college.id = :college_id"
      + " and college.active = true"
      + " and college.deleted = false")
  College findActiveCollegeStructureById(@Param("college_id") Long collegeId);

  @Modifying
  @Transactional
  @Query(nativeQuery = true, value = "update College set deleted = true where deleted = false and id = :college_id ")
  void deleteCollegeById(@Param("college_id") Long collegeId);

  Boolean existsByName(String name);
}
