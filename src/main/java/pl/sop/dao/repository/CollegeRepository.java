package pl.sop.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entitiy.College;

import java.util.List;

@Repository
public interface CollegeRepository extends CrudRepository<College, Long> {

    @Query(value = "SELECT c FROM College c")
    List<College> findAllColleges();

    @Query(value = "SELECT c FROM College c WHERE c.voivodeship_id = :voivo_id")
    List<College> findAllCollegesByVoivodeship_id(@Param("voivo_id") Long voivo_id);
}
