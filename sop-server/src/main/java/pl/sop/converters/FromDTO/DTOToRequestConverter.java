package pl.sop.converters.FromDTO;

import java.time.LocalDate;
import java.util.Date;
import pl.sop.converters.Converter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;
import pl.sop.enums.ERequestStatus;

public class DTOToRequestConverter implements Converter<RequestDTO, Request> {


  @Override
  public Request convert(RequestDTO input) {
    Request request = new Request();

    // Base info
    request.setName(input.getName());
    request.setDescription(input.getDescription());
    // Person data
    request.setPosition(input.getPosition());
    request.setPositionDescription(input.getPositionDescription());
    request.setAmountOfHours(input.getAmountOfHours());
    request.setResponsibilities(input.getResponsibilities());

    request.setPracticeSuperviserName(input.getPracticeSuperviserName());
    request.setPracticeSuperviserLastName(input.getPracticeSuperviserLastName());
    request.setPracticeSuperviseEmail(input.getPracticeSuperviserEmail());
    request.setPracticeSuperviserPhone(input.getPracticeSuperviserPhone());

    request.setInfoAgreement(input.getInfoAgreement());
    request.setProcessingAgreement(input.getProcessingAgreement());


    request.setActualRequestStatus(ERequestStatus.SENT);
    request.setSendRequestDate(LocalDate.now());
    request.setSendRequestStatus(ERequestStatus.SENT);
    return request;
  }
}
