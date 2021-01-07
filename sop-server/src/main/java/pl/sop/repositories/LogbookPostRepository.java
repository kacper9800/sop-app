package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.LogbookPost;

@Repository
public interface LogbookPostRepository extends JpaRepository<LogbookPost, Long> {

  @Query(value = "SELECT logbookPost FROM LogbookPost logbookPost"
      + " LEFT JOIN logbookPost.logbook loogbook"
      + " WHERE loogbook.id = :logbookId")
  List<LogbookPost> findAllLogbookPostsForLogbookId(@Param("logbookId") Long logbookId);
}
