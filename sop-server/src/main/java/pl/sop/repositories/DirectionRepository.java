package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Direction;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

//  @Query(value = "SELECT d FROM Direction d WHERE d.deleted = false and d.collegeId ")
//  List<Direction> findAllDirectionsForCollege(@Param("collegeId") Long collegeId);

  @Query(value = "SELECT d FROM Direction d WHERE d.deleted = false AND d.id = :id ")
  Direction findDirectionById(@Param("id") Long id);

  @Query(value = "SELECT d FROM Direction d "
      + " LEFT JOIN d.institute institute"
      + " LEFT JOIN institute.faculty faculty"
      + " LEFT JOIN faculty.college college"
      + " WHERE d.deleted = false AND college.id = :collegeId")
  List<Direction> findAllDirectionsForCollege(@Param("collegeId") Long collegeId);
}
