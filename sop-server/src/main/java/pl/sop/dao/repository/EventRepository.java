/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entities.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e FROM Event e WHERE e.deleted = false")
    List<Event> findAllEvents();

    @Query(value = "SELECT e FROM Event e LEFT join fetch e.user u where u.id = :id")
    List<Event> findAllEventsForUserId(@Param("id") Long id);

    @Query(value = "SELECT e FROM Event e WHERE e.startDate = null AND e.stopDate = null")
    List<Event> findAllEventWithoutDate();
}
