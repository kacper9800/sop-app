package pl.sop.services;

import com.sun.org.apache.regexp.internal.RE;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToRequestConverter;
import pl.sop.converters.ToDTO.RequestToDTOConverter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;
import pl.sop.repositories.RequestRepository;

@Service
public class RequestService {

  @Autowired
  private RequestRepository requestRepository;

  @Autowired
  private CompanyService companyService;

  private DTOToRequestConverter dtoToRequestConverter = new DTOToRequestConverter();
  private RequestToDTOConverter requestToDTOConverter = new RequestToDTOConverter();


  public ResponseEntity<List<RequestDTO>> getAllRequests(Long internId, Long selectedCollegeId) {
    List<Request> requests = this.requestRepository.getAllRequests(internId, selectedCollegeId);
    List<RequestDTO> requestDTOS  = requests.stream().map(requestToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(requestDTOS);
  }

  public ResponseEntity<RequestDTO> getRequestById(Long id) {
    Request request = this.requestRepository.getOne(id);
    RequestDTO requestDTO = requestToDTOConverter.convert(request);
    return ResponseEntity.ok(requestDTO);
  }

  public ResponseEntity createRequest(RequestDTO requestDTO) {
    if (companyService.isNipValid(requestDTO.getNip())) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    Request request = dtoToRequestConverter.convert(requestDTO);
    this.requestRepository.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // ToDo
  public ResponseEntity updateAsModerator(RequestDTO requestDTO) {
    // Ustawiamy dane dotyczące moderatora i admina
    // Moderator ma do
    Request request = requestRepository.getOne(requestDTO.getId());
    request.setModeratorDecisionDate(requestDTO.getModeratorDecisionDate());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  // ToDo
  public ResponseEntity updateAsAdmin(RequestDTO requestDTO) {
    Request request = requestRepository.getOne(requestDTO.getId());
    request.setAdminDecisionDate(requestDTO.getAdminDecisionDate());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  public ResponseEntity deleteRequest(Long id) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

// Student wypełnia dane, wybiera moderatora (kierownika praktyk)
// Kierownik praktyk dostaje wniosek, sprawdza go
// Do zrobienia okienko przesyłające wniosek do admina
// admin (osoba z instytutu) dostaje wniosek, sprawdza go
// To samo okienko co moderator (info z podsumowania), możliwość dodania feedbacku,
// Może zaakceptować, wtedy leci do studenta i moderatora info
// Może odrzucić, wtedy leci do moderatora info
