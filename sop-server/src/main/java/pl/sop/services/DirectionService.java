package pl.sop.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.sop.converters.FromDTO.DTOToDirectionConverter;
import pl.sop.converters.ToDTO.DirectionToDTOConverter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Direction;
import pl.sop.repositories.DirectionRepository;

@Service
public class DirectionService {

  @Autowired
  private DirectionRepository directionRepository;

  private DTOToDirectionConverter dtoToDirectionConverter = new DTOToDirectionConverter();
  private DirectionToDTOConverter directionToDTOConverter = new DirectionToDTOConverter();

  public DirectionService(DirectionRepository directionRepository) {
    this.directionRepository = directionRepository;
  }

  public ResponseEntity<DirectionDTO> getDirectionById(Long id) {
    Direction direction = directionRepository.findDirectionById(id);
    DirectionDTO directionDTO = directionToDTOConverter.convert(direction);
    return ResponseEntity.ok(directionDTO);
  }

//  public ResponseEntity getAllDirectionForCollege(Long collegeId) {
//    return ResponseEntity.ok(directionRepository.(collegeId));
//  }

  public ResponseEntity<DirectionDTO> saveDirection(DirectionDTO directionDTO) {
    Direction direction = dtoToDirectionConverter.convert(directionDTO);
    this.directionRepository.save(direction);
    return ResponseEntity.ok(directionDTO);
  }


  public ResponseEntity deleteDirection(Long id) {
    this.directionRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
