package pl.sop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Logbook;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, Long> {

}
