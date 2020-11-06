package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.DictionaryDTO;
import pl.sop.entities.Dictionary;

public class DictionaryToDTOConverter implements Converter<Dictionary, DictionaryDTO> {

  @Override
  public DictionaryDTO convert(Dictionary input) {
      DictionaryDTO dictionaryDTO = new DictionaryDTO();
      dictionaryDTO.setName(input.getName());
      dictionaryDTO.setValue(input.getValue());
      dictionaryDTO.setDictionaryType(input.getDictionaryType().name());
      dictionaryDTO.setActive(input.isActive());
      dictionaryDTO.setRemoved(input.isDeleted());
      return dictionaryDTO;
  }
}
