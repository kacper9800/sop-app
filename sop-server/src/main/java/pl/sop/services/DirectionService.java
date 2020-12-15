package pl.sop.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToDirectionConverter;
import pl.sop.converters.ToDTO.DirectionToDTOConverter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Dictionary;
import pl.sop.entities.Direction;
import pl.sop.organizationStructure.Institute;
import pl.sop.organizationStructure.InstituteService;
import pl.sop.repositories.DirectionRepository;

@Service
public class DirectionService {

  @Autowired
  private DirectionRepository directionRepository;

  @Autowired
  private DictionaryService dictionaryService;

  @Autowired
  private InstituteService instituteService;

  private DirectionToDTOConverter directionToDTOConverter = new DirectionToDTOConverter();

  public DirectionService(DirectionRepository directionRepository) {
    this.directionRepository = directionRepository;
  }

  public Direction getById(Long id) {
    Direction direction = directionRepository.findDirectionById(id);
    return direction;
  }

  public ResponseEntity<DirectionDTO> getDirectionById(Long id) {
    Direction direction = directionRepository.findDirectionById(id);
    DirectionDTO directionDTO = directionToDTOConverter.convert(direction);
    return ResponseEntity.ok(directionDTO);
  }

//  public ResponseEntity getAllDirectionForCollege(Long collegeId) {
//    return ResponseEntity.ok(directionRepository.(collegeId));
//  }

  public Direction saveDirection(DirectionDTO directionDTO) {
    Direction direction = new Direction();
    direction.setName(directionDTO.getName());
    direction.setDescription(directionDTO.getDescription());
    direction.setAmountOfStudents(directionDTO.getAmountOfStudents());
    Dictionary dictionary = dictionaryService.getByValue(directionDTO.getStudyMode());
    direction.setStudyMode(dictionary);

    Institute institute = instituteService.getById(directionDTO.getInstituteId());
    direction.setInstitute(institute);
    direction.setStartDate(directionDTO.getStartExpirationDate());
    direction.setEndDate(directionDTO.getEndExpirationDate());
    direction.setActive(Boolean.TRUE);
    direction.setDeleted(Boolean.FALSE);
    return this.directionRepository.save(direction);

  }


  public ResponseEntity deleteDirection(Long id) {
    this.directionRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<List<DirectionDTO>> getAllDirectionForCollege(Long collegeId) {
    List<Direction> directions = this.directionRepository.findAllDirectionsForCollege(collegeId);
    List<DirectionDTO> directionDTOS = directions.stream().map(directionToDTOConverter::convert).collect(Collectors.toList());
    return ResponseEntity.ok(directionDTOS);
  }
}
