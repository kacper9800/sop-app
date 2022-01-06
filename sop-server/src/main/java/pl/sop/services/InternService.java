package pl.sop.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.dto.DictionaryDTO;
import pl.sop.dto.InternDTO;
import pl.sop.repositories.InternshipRepository;

@Service
public class InternService  {

  @Autowired
  private InternshipRepository internshipRepository;

//  public ResponseEntity<List<InternDTO>> getAllInternsForCollege(Long collegeId) {
//
//
//  }
//
//  public ResponseEntity<List<DictionaryDTO>> getAllInternsForInstitute(Long instituteId) {
//  }
}
