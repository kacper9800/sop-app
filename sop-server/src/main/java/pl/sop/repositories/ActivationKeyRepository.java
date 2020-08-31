package pl.sop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sop.entities.Token;

@Repository
public interface ActivationKeyRepository extends JpaRepository<Token, Long> {

  @Query(value = "select token from Token token"
      + " left join fetch token.college c "
      + " where token.deleted = false and c.id = :collegeId")
  List<Token> getAllTokensByCollegeId(@Param("collegeId") Long id);

  @Query(value = "select token from Token token where token.deleted = false and token.id = :id")
  List<Token> getTokenById(@Param("id") Long id);


  @Query(value = "SELECT distinct token from Token token"
      + " WHERE token.value = :token"
      + " and token.active = true"
      + " and token.remainingUses > 0")
  Token findValidTokenByValue(@Param("token") String token);

//  @Query(value = "select token from Token token "
//      + "where token.createdBy = :id and token.deleted = false")
//  List<Token> findAllTokensForLoggedUser(@Param("id") Long userId);
}
