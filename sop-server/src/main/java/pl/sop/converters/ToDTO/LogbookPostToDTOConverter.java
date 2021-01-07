package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.LogbookPostDTO;
import pl.sop.entities.LogbookPost;

public class LogbookPostToDTOConverter implements Converter<LogbookPost, LogbookPostDTO> {

  @Override
  public LogbookPostDTO convert(LogbookPost input) {
    LogbookPostDTO logbookPostDTO = new LogbookPostDTO();
    logbookPostDTO.setDescription(input.getDescription());
    logbookPostDTO.setAmountOfHours(input.getHours());
    logbookPostDTO.setDate(input.getDate());
    return logbookPostDTO;
  }
}
