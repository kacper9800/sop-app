package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.LogbookDTO;
import pl.sop.entities.Logbook;

public class LogbookToDTOConverter implements Converter<Logbook, LogbookDTO> {

  @Override
  public LogbookDTO convert(Logbook input) {
    LogbookDTO logbookDTO = new LogbookDTO();
    logbookDTO.setId(input.getId());
    logbookDTO.setName(input.getName());
    logbookDTO.setDescription(input.getDescription());
    logbookDTO.setInternId(input.getIntern().getId());
    logbookDTO.setInternName(input.getIntern().getFirstName() + " " + input.getIntern().getLastName());

    logbookDTO.setCollegeId(input.getCollege().getId());
    logbookDTO.setCollegeName(input.getCollege().getName());

    logbookDTO.setInstituteId(input.getInstitute().getId());
    logbookDTO.setInstituteName(input.getInstitute().getName());
    return logbookDTO;
  }
}
