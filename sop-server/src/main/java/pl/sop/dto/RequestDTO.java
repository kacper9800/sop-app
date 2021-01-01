package pl.sop.dto;

import java.time.LocalDate;
import pl.sop.enums.ERequestStatus;

public class RequestDTO {

  private Long id;
  private String name;
  private String description;

  private Long requestTypeId;
  private String requestTypeName;

  private Long collegeId;
  private String collegeName;
  private Long instituteId;
  private String instituteName;
  private Long internId;
  private String internName;

  // Intern data
  private String position;
  private String positionDescription;
  private Integer amountOfHours;
  private String responsibilities;

  // Company data
  private String nip;
  private String companyName;
  private String companyDescription;
  private String departmentName;

  // Superviser data
  private String practiceSuperviserName;
  private String practiceSuperviserLastName;
  private String practiceSuperviserPhone;
  private String practiceSuperviserEmail;

  // Agreements
  private Boolean infoAgreement;
  private Boolean processingAgreement;

  // Receivers
  private Long moderatorId;
  private String moderatorName;

  private Long adminId;
  private String adminName;

  // Status
  private ERequestStatus actualRequestStatus;

  private LocalDate sendRequestDate;
  private ERequestStatus sendRequestStatus;

  private LocalDate moderatorDecisionDate;
  private ERequestStatus moderatorDecisionStatus;
  private String moderatorDecisionFeedback;

  private LocalDate adminDecisionDate;
  private ERequestStatus adminDecisionStatus;
  private String adminDecisionFeedback;

  private Boolean active;
  private Boolean removed;

  public RequestDTO() {
  }

  public RequestDTO(Long id, String name, String description, Long requestTypeId,
      String requestTypeName, Long collegeId, String collegeName, Long instituteId,
      String instituteName, Long internId, String internName, String position,
      String positionDescription, Integer amountOfHours, String responsibilities,
      String nip, String companyName, String companyDescription, String departmentName,
      String practiceSuperviserName, String practiceSuperviserLastName,
      String practiceSuperviserPhone, String practiceSuperviserEmail, Boolean infoAgreement,
      Boolean processingAgreement, Long moderatorId, String moderatorName, Long adminId,
      String adminName, ERequestStatus actualRequestStatus, LocalDate sendRequestDate,
      ERequestStatus sendRequestStatus, LocalDate moderatorDecisionDate,
      ERequestStatus moderatorDecisionStatus, String moderatorDecisionFeedback,
      LocalDate adminDecisionDate, ERequestStatus adminDecisionStatus,
      String adminDecisionFeedback, Boolean active, Boolean removed) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.requestTypeId = requestTypeId;
    this.requestTypeName = requestTypeName;
    this.collegeId = collegeId;
    this.collegeName = collegeName;
    this.instituteId = instituteId;
    this.instituteName = instituteName;
    this.internId = internId;
    this.internName = internName;
    this.position = position;
    this.positionDescription = positionDescription;
    this.amountOfHours = amountOfHours;
    this.responsibilities = responsibilities;
    this.nip = nip;
    this.companyName = companyName;
    this.companyDescription = companyDescription;
    this.departmentName = departmentName;
    this.practiceSuperviserName = practiceSuperviserName;
    this.practiceSuperviserLastName = practiceSuperviserLastName;
    this.practiceSuperviserPhone = practiceSuperviserPhone;
    this.practiceSuperviserEmail = practiceSuperviserEmail;
    this.infoAgreement = infoAgreement;
    this.processingAgreement = processingAgreement;
    this.moderatorId = moderatorId;
    this.moderatorName = moderatorName;
    this.adminId = adminId;
    this.adminName = adminName;
    this.actualRequestStatus = actualRequestStatus;
    this.sendRequestDate = sendRequestDate;
    this.sendRequestStatus = sendRequestStatus;
    this.moderatorDecisionDate = moderatorDecisionDate;
    this.moderatorDecisionStatus = moderatorDecisionStatus;
    this.moderatorDecisionFeedback = moderatorDecisionFeedback;
    this.adminDecisionDate = adminDecisionDate;
    this.adminDecisionStatus = adminDecisionStatus;
    this.adminDecisionFeedback = adminDecisionFeedback;
    this.active = active;
    this.removed = removed;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getRequestTypeId() {
    return requestTypeId;
  }

  public void setRequestTypeId(Long requestTypeId) {
    this.requestTypeId = requestTypeId;
  }

  public String getRequestTypeName() {
    return requestTypeName;
  }

  public void setRequestTypeName(String requestTypeName) {
    this.requestTypeName = requestTypeName;
  }

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public Long getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(Long instituteId) {
    this.instituteId = instituteId;
  }

  public String getInstituteName() {
    return instituteName;
  }

  public void setInstituteName(String instituteName) {
    this.instituteName = instituteName;
  }

  public Long getInternId() {
    return internId;
  }

  public void setInternId(Long internId) {
    this.internId = internId;
  }

  public String getInternName() {
    return internName;
  }

  public void setInternName(String internName) {
    this.internName = internName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPositionDescription() {
    return positionDescription;
  }

  public void setPositionDescription(String positionDescription) {
    this.positionDescription = positionDescription;
  }

  public Integer getAmountOfHours() {
    return amountOfHours;
  }

  public void setAmountOfHours(Integer amountOfHours) {
    this.amountOfHours = amountOfHours;
  }

  public String getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(String responsibilities) {
    this.responsibilities = responsibilities;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyDescription() {
    return companyDescription;
  }

  public void setCompanyDescription(String companyDescription) {
    this.companyDescription = companyDescription;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getPracticeSuperviserName() {
    return practiceSuperviserName;
  }

  public void setPracticeSuperviserName(String practiceSuperviserName) {
    this.practiceSuperviserName = practiceSuperviserName;
  }

  public String getPracticeSuperviserLastName() {
    return practiceSuperviserLastName;
  }

  public void setPracticeSuperviserLastName(String practiceSuperviserLastName) {
    this.practiceSuperviserLastName = practiceSuperviserLastName;
  }

  public String getPracticeSuperviserPhone() {
    return practiceSuperviserPhone;
  }

  public void setPracticeSuperviserPhone(String practiceSuperviserPhone) {
    this.practiceSuperviserPhone = practiceSuperviserPhone;
  }

  public String getPracticeSuperviserEmail() {
    return practiceSuperviserEmail;
  }

  public void setPracticeSuperviserEmail(String practiceSuperviserEmail) {
    this.practiceSuperviserEmail = practiceSuperviserEmail;
  }

  public Boolean getInfoAgreement() {
    return infoAgreement;
  }

  public void setInfoAgreement(Boolean infoAgreement) {
    this.infoAgreement = infoAgreement;
  }

  public Boolean getProcessingAgreement() {
    return processingAgreement;
  }

  public void setProcessingAgreement(Boolean processingAgreement) {
    this.processingAgreement = processingAgreement;
  }

  public Long getModeratorId() {
    return moderatorId;
  }

  public void setModeratorId(Long moderatorId) {
    this.moderatorId = moderatorId;
  }

  public String getModeratorName() {
    return moderatorName;
  }

  public void setModeratorName(String moderatorName) {
    this.moderatorName = moderatorName;
  }

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public ERequestStatus getActualRequestStatus() {
    return actualRequestStatus;
  }

  public void setActualRequestStatus(ERequestStatus actualRequestStatus) {
    this.actualRequestStatus = actualRequestStatus;
  }

  public LocalDate getSendRequestDate() {
    return sendRequestDate;
  }

  public void setSendRequestDate(LocalDate sendRequestDate) {
    this.sendRequestDate = sendRequestDate;
  }

  public ERequestStatus getSendRequestStatus() {
    return sendRequestStatus;
  }

  public void setSendRequestStatus(ERequestStatus sendRequestStatus) {
    this.sendRequestStatus = sendRequestStatus;
  }

  public LocalDate getModeratorDecisionDate() {
    return moderatorDecisionDate;
  }

  public void setModeratorDecisionDate(LocalDate moderatorDecisionDate) {
    this.moderatorDecisionDate = moderatorDecisionDate;
  }

  public ERequestStatus getModeratorDecisionStatus() {
    return moderatorDecisionStatus;
  }

  public void setModeratorDecisionStatus(ERequestStatus moderatorDecisionStatus) {
    this.moderatorDecisionStatus = moderatorDecisionStatus;
  }

  public String getModeratorDecisionFeedback() {
    return moderatorDecisionFeedback;
  }

  public void setModeratorDecisionFeedback(String moderatorDecisionFeedback) {
    this.moderatorDecisionFeedback = moderatorDecisionFeedback;
  }

  public LocalDate getAdminDecisionDate() {
    return adminDecisionDate;
  }

  public void setAdminDecisionDate(LocalDate adminDecisionDate) {
    this.adminDecisionDate = adminDecisionDate;
  }

  public ERequestStatus getAdminDecisionStatus() {
    return adminDecisionStatus;
  }

  public void setAdminDecisionStatus(ERequestStatus adminDecisionStatus) {
    this.adminDecisionStatus = adminDecisionStatus;
  }

  public String getAdminDecisionFeedback() {
    return adminDecisionFeedback;
  }

  public void setAdminDecisionFeedback(String adminDecisionFeedback) {
    this.adminDecisionFeedback = adminDecisionFeedback;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }
}
