
package pl.sop.converters.ToDTO;


import java.util.List;
import java.util.stream.Collectors;
import pl.sop.converters.Converter;
import pl.sop.dto.CollegeStructureDTO;
import pl.sop.dto.FacultyDTO;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.Faculty;

public class CollegeToCollegeStructureDTOConverter implements
    Converter<College, CollegeStructureDTO> {

  private final FacultyToDTOConverter facultyToDTOConverter = new FacultyToDTOConverter();

  @Override
  public CollegeStructureDTO convert(College input) {
    CollegeStructureDTO collegeStructureDTO = new CollegeStructureDTO();
    collegeStructureDTO.setId(input.getId());
    collegeStructureDTO.setCollegeName(input.getName());
    if (input.getFaculties() != null) {
      List<Faculty> facultyList = input.getFaculties();
      List<FacultyDTO> facultyDTOList = facultyList.stream().map(faculty ->
          facultyToDTOConverter.convert(faculty)).collect(Collectors.toList());
      collegeStructureDTO.setFaculties(facultyDTOList);
    } else {
      collegeStructureDTO.setFaculties(null);
    }
    return collegeStructureDTO;
  }
}
