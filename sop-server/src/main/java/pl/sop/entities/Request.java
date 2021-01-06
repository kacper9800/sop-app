package pl.sop.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.enums.ERequestStatus;
import pl.sop.entities.organizationStructure.Institute;

@Entity
@Table(name = "requests")
public class Request extends BasicEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "intern_id", nullable = false)
  private User intern;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "request_type_id")
  @Fetch(FetchMode.JOIN)
  private Dictionary requestType;

  @ManyToOne
  @JoinColumn(name = "institute_id", nullable = false)
  private Institute institute;

  // Intern data
  @Column(name = "position")
  private String position;

  @Column(name = "positionDescription")
  private String positionDescription;

  @Column(name = "amountOfHours")
  private Integer amountOfHours;

  @Column(name = "responsibilities")
  private String responsibilities;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @Column(name = "practice_superviser_name")
  private String practiceSuperviserName;

  @Column(name = "practice_superviser_last_name")
  private String practiceSuperviserLastName;

  @Column(name = "practice_superviser_phone")
  private String practiceSuperviserPhone;

  @Column(name = "practice_superviser_email")
  private String practiceSuperviseEmail;

  @Column(name = "info_agreement")
  private Boolean infoAgreement;

  @Column(name = "processing_agreement")
  private Boolean processingAgreement;

  @ManyToOne
  @JoinColumn(name = "moderator_id", nullable = false)
  private User moderator;

  @ManyToOne
  @JoinColumn(name = "admin_id", nullable = false)
  private User admin;

  @Column(name = "actual_request_status")
  @Enumerated(EnumType.STRING)
  private ERequestStatus actualRequestStatus;

  @Column(name = "send_request_date")
  private LocalDate sendRequestDate;

  @Column(name = "send_request_status")
  @Enumerated(EnumType.STRING)
  private ERequestStatus sendRequestStatus;

  @Column(name = "moderator_decision_date")
  private LocalDate moderatorDecisionDate;

  @Column(name = "moderator_decision_status")
  @Enumerated(EnumType.STRING)
  private ERequestStatus moderatorDecisionStatus;

  @Column(name = "moderator_decision_feedback")
  private String moderatorDecisionFeedback;

  @Column(name = "admin_decision_date")
  private LocalDate adminDecisionDate;

  @Column(name = "admin_decision_status")
  @Enumerated(EnumType.STRING)
  private ERequestStatus adminDecisionStatus;

  @Column(name = "admin_decision_feedback")
  private String adminDecisionFeedback;

  public Request() {
  }

  public Request(String name, String description, User intern, Dictionary requestType,
      Institute institute, String position, String positionDescription,
      Integer amountOfHours, String responsibilities, Company company,
      String practiceSuperviserName, String practiceSuperviserLastName,
      String practiceSuperviserPhone, String practiceSuperviseEmail, Boolean infoAgreement,
      Boolean processingAgreement, User moderator, User admin,
      ERequestStatus actualRequestStatus, LocalDate sendRequestDate,
      ERequestStatus sendRequestStatus, LocalDate moderatorDecisionDate,
      ERequestStatus moderatorDecisionStatus, String moderatorDecisionFeedback,
      LocalDate adminDecisionDate, ERequestStatus adminDecisionStatus,
      String adminDecisionFeedback) {
    this.name = name;
    this.description = description;
    this.intern = intern;
    this.requestType = requestType;
    this.institute = institute;
    this.position = position;
    this.positionDescription = positionDescription;
    this.amountOfHours = amountOfHours;
    this.responsibilities = responsibilities;
    this.company = company;
    this.practiceSuperviserName = practiceSuperviserName;
    this.practiceSuperviserLastName = practiceSuperviserLastName;
    this.practiceSuperviserPhone = practiceSuperviserPhone;
    this.practiceSuperviseEmail = practiceSuperviseEmail;
    this.infoAgreement = infoAgreement;
    this.processingAgreement = processingAgreement;
    this.moderator = moderator;
    this.admin = admin;
    this.actualRequestStatus = actualRequestStatus;
    this.sendRequestDate = sendRequestDate;
    this.sendRequestStatus = sendRequestStatus;
    this.moderatorDecisionDate = moderatorDecisionDate;
    this.moderatorDecisionStatus = moderatorDecisionStatus;
    this.moderatorDecisionFeedback = moderatorDecisionFeedback;
    this.adminDecisionDate = adminDecisionDate;
    this.adminDecisionStatus = adminDecisionStatus;
    this.adminDecisionFeedback = adminDecisionFeedback;
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

  public User getIntern() {
    return intern;
  }

  public void setIntern(User intern) {
    this.intern = intern;
  }

  public Dictionary getRequestType() {
    return requestType;
  }

  public void setRequestType(Dictionary requestType) {
    this.requestType = requestType;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
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

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
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

  public String getPracticeSuperviseEmail() {
    return practiceSuperviseEmail;
  }

  public void setPracticeSuperviseEmail(String practiceSuperviseEmail) {
    this.practiceSuperviseEmail = practiceSuperviseEmail;
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

  public User getModerator() {
    return moderator;
  }

  public void setModerator(User moderator) {
    this.moderator = moderator;
  }

  public User getAdmin() {
    return admin;
  }

  public void setAdmin(User admin) {
    this.admin = admin;
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
}
