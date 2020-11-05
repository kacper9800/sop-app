package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Direction;

public class DTOToDirectionConverter implements Converter<DirectionDTO, Direction> {

  @Override
  public Direction convert(DirectionDTO input) {
    Direction direction = new Direction();
    direction.setName(input.getName());
    direction.setDescription(input.getDescription());
    direction.setAmountOfStudents(input.getAmountOfStudents());
    direction.setStudyMode(input.getStudyMode());
    direction.setStartDate(input.getStartDate());
    direction.setEndDate(input.getEndDate());
    return direction;
  }
}
