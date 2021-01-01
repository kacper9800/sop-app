package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;

public class RequestToDTOConverter implements Converter<Request, RequestDTO> {

  @Override
  public RequestDTO convert(Request input) {
    RequestDTO requestDTO = new RequestDTO();
    requestDTO.setName(input.getName());
    requestDTO.setDescription(input.getDescription());
    requestDTO.setInternId(input.getIntern().getId());
    requestDTO.setInternName(input.getIntern().getFirstName() + " " + input.getIntern().getLastName() );

    requestDTO.setInstituteId(input.getInstitute().getId());
    requestDTO.setInstituteName(input.getInstitute().getName());

    requestDTO.setModeratorId(input.getModerator().getId());
    requestDTO.setModeratorName(input.getModerator().getFirstName() + " " + input.getModerator().getLastName());

    return requestDTO;
  }
}
