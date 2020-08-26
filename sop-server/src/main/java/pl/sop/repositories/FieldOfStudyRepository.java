package pl.sop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sop.entities.FieldOfStudy;

@Repository
public interface FieldOfStudyRepository extends CrudRepository<FieldOfStudy, Long> {

}
