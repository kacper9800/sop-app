package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.CollegeDTO;
import pl.sop.entities.organizationStructure.College;

public class DTOToCollegeConverter implements Converter<CollegeDTO, College> {

  @Override
  public College convert(CollegeDTO input) {
    College college = new College();
    if (input.getId() != null) {
      college.setId(input.getId());
    }
    college.setName(input.getName());
    college.setActive(input.getActive());
    college.setDeleted(input.getDeleted());
    return college;
  }
}
