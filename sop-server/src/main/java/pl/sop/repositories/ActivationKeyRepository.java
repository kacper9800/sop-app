package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.token.Token;

@Repository
public interface ActivationKeyRepository extends JpaRepository<Token, Long> {

  @Query(value = "select token from Token token where token.deleted = false and token.collegeId = :collegeId")
  public List<Token> getAllTokensByCollegeId(@Param("collegeId") Long id);

  @Query(value = "select token from Token token where token.deleted = false and token.id = :collegeId")
  public List<Token> getTokenById(@Param("collegeId") Long id);

}
