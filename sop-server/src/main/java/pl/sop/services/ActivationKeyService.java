package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.ToDTO.ActivationKeyToDTOConverter;
import pl.sop.dto.TokenDTO;
import pl.sop.repositories.ActivationKeyRepository;
import pl.sop.entities.Token;

@Service
public class ActivationKeyService {

  @Autowired
  private ActivationKeyRepository activationKeyRepository;

  private final ActivationKeyToDTOConverter activationKeyToDTOConverter = new ActivationKeyToDTOConverter();


  public ResponseEntity getAllTokensForCompany(Long id) {
    List<Token> tokens = activationKeyRepository.getAllTokensByCollegeId(id);
    List<TokenDTO> tokenDTOS = tokens.stream().map(additionalTestResult ->
        activationKeyToDTOConverter.convert(additionalTestResult)).collect(Collectors.toList());
    return new ResponseEntity(tokenDTOS, HttpStatus.OK);

  }

  public Token getTokenByValue(String tokenValue) {
    return activationKeyRepository.findValidTokenByValue(tokenValue);
  }

  public boolean isValidTokenForUser(String tokenValue) {
    Token token = activationKeyRepository.findValidTokenByValue(tokenValue);
    if (token != null) {
      return true;
    }
    return false;
  }

  public boolean isValidTokenForCollege(Long collegeId, String tokenValue) {
    Token token = activationKeyRepository.findValidTokenByValue(tokenValue);
    if (token != null && token.getCollege().getId().equals(collegeId)) {
      return true;
    }
    return false;
  }
}
