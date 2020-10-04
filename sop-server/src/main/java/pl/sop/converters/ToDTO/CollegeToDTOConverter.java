package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.CollegeDTO;
import pl.sop.organizationStructure.College;

public class CollegeToDTOConverter implements Converter<College, CollegeDTO> {

  @Override
  public CollegeDTO convert(College input) {
    CollegeDTO collegeDTO = new CollegeDTO();
    collegeDTO.setId(input.getId());
    collegeDTO.setName(input.getName());
    collegeDTO.setActive(input.getActive());
    collegeDTO.setDeleted(input.getDeleted());
    return collegeDTO;
  }
}
