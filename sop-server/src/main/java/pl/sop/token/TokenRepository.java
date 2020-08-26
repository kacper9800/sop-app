package pl.sop.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  @Query(value = "SELECT distinct token from Token token "
      + " WHERE token.value = :token"
      + " and token.active = true"
      + " and token.remainingUses > 0")
  Token findValidTokenByValue(@Param("token") String token);

  @Query(value = "select token from Token token "
      + "where token.createdBy = :id and token.deleted = false")
  List<Token> findAllTokensForLoggedUser(@Param("id") Long userId);
}

