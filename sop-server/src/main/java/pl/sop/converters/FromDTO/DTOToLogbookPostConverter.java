package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.LogbookPostDTO;
import pl.sop.entities.LogbookPost;

public class DTOToLogbookPostConverter implements Converter<LogbookPostDTO, LogbookPost> {

  @Override
  public LogbookPost convert(LogbookPostDTO input) {
    LogbookPost logbookPost = new LogbookPost();
    logbookPost.setDescription(input.getDescription());
    logbookPost.setDate(input.getDate());
    logbookPost.setHours(input.getAmountOfHours());
    return logbookPost;
  }
}
