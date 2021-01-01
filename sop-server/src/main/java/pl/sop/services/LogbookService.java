package pl.sop.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.entities.Logbook;
import pl.sop.repositories.LogbookRepository;

@Service
public class LogbookService {

  @Autowired
  private LogbookRepository logbookRepository;

  public ResponseEntity<List<Logbook>> getAllLogbooks() {
    return ResponseEntity.ok(logbookRepository.findAll());
  }
}
