package pl.sop.converters.ToDTO;

import java.util.List;
import pl.sop.converters.Converter;
import pl.sop.dto.LogbookDTO;
import pl.sop.entities.Logbook;
import pl.sop.entities.LogbookPost;

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

    logbookDTO.setCompanyId(input.getInternship().getRequest().getCompany().getId());
    logbookDTO.setCompanyName(input.getInternship().getRequest().getCompany().getName());
    logbookDTO.setAmountOfHours(input.getInternship().getRequest().getAmountOfHours());

    logbookDTO.setPosition(input.getInternship().getRequest().getPosition());
    List<LogbookPost> logbookPosts = input.getLogbookPosts();
    if (logbookPosts.size() > 0) {
      Integer actualAmountOfHours = 0;
      for (LogbookPost logbookPost: logbookPosts) {
        actualAmountOfHours += logbookPost.getHours();
      }
      logbookDTO.setActualAmountOfHours(actualAmountOfHours);
    } else {
      logbookDTO.setActualAmountOfHours(0);
    }

    logbookDTO.setInstituteId(input.getInstitute().getId());
    logbookDTO.setInstituteName(input.getInstitute().getName());
    logbookDTO.setActive(input.isActive());
    return logbookDTO;
  }
}
