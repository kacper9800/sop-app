package pl.sop.converters.FromDTO;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sop.converters.Converter;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Dictionary;
import pl.sop.entities.Direction;
import pl.sop.services.DictionaryService;

public class DTOToDirectionConverter implements Converter<DirectionDTO, Direction> {

  private DictionaryService dictionaryService;

  public DTOToDirectionConverter(DictionaryService dictionaryService) {
    this.dictionaryService = dictionaryService;
  }

  @Override
  public Direction convert(DirectionDTO input){
    return null;
  }
}
