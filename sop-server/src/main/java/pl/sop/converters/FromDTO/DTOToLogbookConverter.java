package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.LogbookDTO;
import pl.sop.entities.Logbook;

public class DTOToLogbookConverter implements Converter<LogbookDTO, Logbook> {

  @Override
  public Logbook convert(LogbookDTO input) {
    Logbook logbook = new Logbook();
    logbook.setName(input.getName());
    logbook.setDescription(input.getDescription());
    return logbook;
  }
}
