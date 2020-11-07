package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Direction;

public class DirectionToDTOConverter implements Converter<Direction, DirectionDTO> {

  @Override
  public DirectionDTO convert(Direction input) {
    DirectionDTO directionDTO = new DirectionDTO();
    directionDTO.setId(input.getId());
    directionDTO.setName(input.getName());
    directionDTO.setDescription(input.getDescription());
    directionDTO.setStartExpirationDate(input.getStartDate());
    directionDTO.setEndExpirationDate(input.getEndDate());
    directionDTO.setAmountOfStudents(input.getAmountOfStudents());
    directionDTO.setStudyMode(input.getStudyMode().getValue());
    directionDTO.setInstituteId(input.getInstitute().getId());
    directionDTO.setInstituteName(input.getInstitute().getName());
    directionDTO.setFacultyName(input.getInstitute().getFaculty().getName());
    directionDTO.setActive(input.isActive());
    directionDTO.setRemoved(input.isDeleted());
    return directionDTO;
  }
}
