package pl.sop.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Autowired
  private TokenRepository tokenRepository;

  public TokenService() {
  }
  public Token getTokenByValue(String tokenValue) {
    return tokenRepository.findValidTokenByValue(tokenValue);
  }

  public boolean isValidTokenForUser(String tokenValue) {
    Token token = tokenRepository.findValidTokenByValue(tokenValue);
    if (token != null) {
      return true;
    }
    return false;
  }

  public boolean isValidTokenForCollege(Long collegeId, String tokenValue) {
    Token token = tokenRepository.findValidTokenByValue(tokenValue);
    if (token != null && token.getCollegeId().equals(collegeId)) {
      return true;
    }
    return false;
  }
}
