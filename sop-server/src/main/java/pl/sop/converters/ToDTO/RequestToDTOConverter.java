package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dto.RequestDTO;
import pl.sop.entities.Request;

public class RequestToDTOConverter implements Converter<Request, RequestDTO> {

  @Override
  public RequestDTO convert(Request input) {
    RequestDTO requestDTO = new RequestDTO();

    requestDTO.setId(input.getId());

    //Base info
    requestDTO.setName(input.getName());
    requestDTO.setDescription(input.getDescription());

    //Intern data
    requestDTO.setInternId(input.getIntern().getId());
    requestDTO.setInternName(input.getIntern().getFirstName() + " " + input.getIntern().getLastName() );
    requestDTO.setPosition(input.getPosition());
    requestDTO.setPositionDescription(input.getPositionDescription());
    requestDTO.setAmountOfHours(input.getAmountOfHours());
    requestDTO.setResponsibilities(input.getResponsibilities());
    if (input.getAdmin() != null) {
      requestDTO.setAdminId(input.getAdmin().getId());
      requestDTO.setAdminName(input.getAdmin().getFirstName() + " " + input.getAdmin().getLastName());
    }
    requestDTO.setRequestTypeId(input.getRequestType().getId());
    requestDTO.setRequestTypeName(input.getRequestType().getValue());

    requestDTO.setCompanyId(input.getCompany().getId());
    requestDTO.setCompanyName(input.getCompany().getName());

    requestDTO.setInstituteId(input.getInstitute().getId());
    requestDTO.setInstituteName(input.getInstitute().getName());

    requestDTO.setModeratorId(input.getModerator().getId());
    requestDTO.setModeratorName(input.getModerator().getFirstName() + " " + input.getModerator().getLastName());


    requestDTO.setPracticeSuperviserName(input.getPracticeSuperviserName());
    requestDTO.setPracticeSuperviserLastName(input.getPracticeSuperviserLastName());
    requestDTO.setPracticeSuperviserEmail(input.getPracticeSuperviseEmail());
    requestDTO.setPracticeSuperviserPhone(input.getPracticeSuperviserPhone());

    requestDTO.setInfoAgreement(input.getInfoAgreement());
    requestDTO.setProcessingAgreement(input.getProcessingAgreement());

    requestDTO.setActualRequestStatus(input.getActualRequestStatus());
    requestDTO.setSendRequestDate(input.getSendRequestDate());
    requestDTO.setSendRequestStatus(input.getSendRequestStatus());


    if (input.getModeratorDecisionStatus() !=  null) {
      requestDTO.setModeratorDecisionDate(input.getModeratorDecisionDate());
      requestDTO.setModeratorDecisionFeedback(input.getModeratorDecisionFeedback());
      requestDTO.setModeratorDecisionStatus(input.getModeratorDecisionStatus());
    }

    if (input.getAdminDecisionStatus() != null) {
      requestDTO.setAdminDecisionDate(input.getAdminDecisionDate());
      requestDTO.setAdminDecisionFeedback(input.getAdminDecisionFeedback());
      requestDTO.setAdminDecisionStatus(input.getAdminDecisionStatus());
    }


    return requestDTO;
  }
}
