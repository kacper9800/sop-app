package pl.sop.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entitiy.FieldOfStudy;

@Repository
public interface FieldOfStudyRepository extends CrudRepository<FieldOfStudy, Long> {

}
