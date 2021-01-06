package pl.sop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import liquibase.pro.packaged.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.ToDTO.DictionaryToDTOConverter;
import pl.sop.dto.DictionaryDTO;
import pl.sop.entities.Dictionary;
import pl.sop.enums.EDictionaryType;
import pl.sop.repositories.DictionaryRepository;

@Service
public class DictionaryService {

  @Autowired
  private DictionaryRepository dictionaryRepository;

  private DictionaryToDTOConverter dictionaryToDTOConverter = new DictionaryToDTOConverter();


  public ResponseEntity<List<DictionaryDTO>> getAllStudyModes() {
    List<Dictionary> dictionaries = dictionaryRepository.getAllStudyModes();
    if (dictionaries.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    List<DictionaryDTO> dictionaryDTOS = dictionaries.stream()
        .map(dictionaryToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(dictionaryDTOS);
  }


  public ResponseEntity<List<DictionaryDTO>> getAllSexTypes() {
    List<Dictionary> dictionaries = dictionaryRepository.getAllSexTypes();
    if (dictionaries.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    List<DictionaryDTO> dictionaryDTOS = dictionaries.stream()
        .map(dictionaryToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(dictionaryDTOS);
  }

  public ResponseEntity<List<DictionaryDTO>> getAllAcademicDegrees() {
    List<Dictionary> dictionaries = dictionaryRepository.getAllAcademicDegrees();
    if (dictionaries.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    List<DictionaryDTO> dictionaryDTOS = dictionaries.stream()
        .map(dictionaryToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(dictionaryDTOS);
  }

  public ResponseEntity<List<DictionaryDTO>> getAllRequestDecisionStatuses() {
    List<DictionaryDTO> dictionaryDTOS = new ArrayList<>();
    DictionaryDTO accept = new DictionaryDTO();
    accept.setDictionaryType(EDictionaryType.REQUEST_DECISION_STATUS.toString());
    accept.setValue("ACCEPTED");

    DictionaryDTO reject = new DictionaryDTO();
    reject.setDictionaryType(EDictionaryType.REQUEST_DECISION_STATUS.toString());
    reject.setValue("REJECTED");

    dictionaryDTOS.add(accept);
    dictionaryDTOS.add(reject);
    return ResponseEntity.ok(dictionaryDTOS);
  }

  public ResponseEntity<List<DictionaryDTO>> getAllRequestTypes() {
    List<Dictionary> dictionaries = dictionaryRepository.getAllRequestTypes();
    if (dictionaries.size() == 0) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    List<DictionaryDTO> dictionaryDTOS = dictionaries.stream()
        .map(dictionaryToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(dictionaryDTOS);
  }

  public Dictionary getByValue(String value) {
    Dictionary dictionary = this.dictionaryRepository.getByValue(value);
    return dictionary;
  }
}
