package pl.sop.converters.ToDTO;

import java.util.List;
import java.util.stream.Collectors;
import pl.sop.converters.Converter;
import pl.sop.dto.FacultyDTO;
import pl.sop.dto.InstituteDTO;
import pl.sop.entities.organizationStructure.Faculty;

public class FacultyToDTOConverter implements Converter<Faculty, FacultyDTO> {

  private final InstituteToDTOConverter instituteToDTOConverter = new InstituteToDTOConverter();

  @Override
  public FacultyDTO convert(Faculty input) {
    FacultyDTO facultyDTO = new FacultyDTO();
    facultyDTO.setId(input.getId());
    facultyDTO.setName(input.getName());
    if (input.getInstitutes() != null) {
      List<InstituteDTO> instituteDTOList = input.getInstitutes().stream().map(institute ->
          instituteToDTOConverter.convert(institute)).collect(Collectors.toList());
      facultyDTO.setInstitutes(instituteDTOList);
    } else {
      facultyDTO.setInstitutes(null);
    }
    return facultyDTO;
  }
}
