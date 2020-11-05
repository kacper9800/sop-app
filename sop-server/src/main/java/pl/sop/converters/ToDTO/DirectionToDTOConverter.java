package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Direction;

public class DirectionToDTOConverter implements Converter<Direction, DirectionDTO> {

  @Override
  public DirectionDTO convert(Direction input) {
    DirectionDTO directionDTO = new DirectionDTO();
    directionDTO.setName(input.getName());
    directionDTO.setDescription(input.getDescription());
    directionDTO.setStartDate(input.getStartDate());
    directionDTO.setEndDate(input.getEndDate());
    directionDTO.setAmountOfStudents(input.getAmountOfStudents());
    directionDTO.setStudyMode(input.getStudyMode());
    return directionDTO;
  }
}
