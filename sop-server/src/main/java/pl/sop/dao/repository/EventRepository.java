/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
