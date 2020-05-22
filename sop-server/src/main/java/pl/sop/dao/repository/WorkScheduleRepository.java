package pl.sop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sop.dao.entities.WorkSchedule;

import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {

    @Query(value = "SELECT ws from WorkSchedule ws")
    List<WorkSchedule> findAllWorkSchedules();
}
