package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.sop.organizationStructure.Institute;

@Entity
@Table(name = "DIRECTIONS")
public class Request extends BasicEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  // Step 1. Sending request from intern to moderator
  @Column(name = "intern_id")
  private Long internId;

  @Column(name = "moderator_id")
  private Long moderatorId;

  @Column(name = "admin_id")
  private Long adminId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "intern_to_moderator_status")
  @Fetch(FetchMode.JOIN)
  private Dictionary internToModeratorStatus;

  @Column(name = "intern_to_moderator_action_date")
  private LocalDate internToModeratorActionDate;

  // Step 2. Sending request from moderator to collegeAdmin
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "moderator_to_admin_status")
  @Fetch(FetchMode.JOIN)
  private Dictionary moderatorToAdminStatus;

  @Column(name = "moderator_to_admin_action_date")
  private LocalDate moderatorToAdminActionDate;

  // Step 3. Info from admin to moderator
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admin_to_moderator_status")
  @Fetch(FetchMode.JOIN)
  private Dictionary adminToModeratorStatus;

  @Column(name = "admin_to_moderator_action_date")
  private LocalDate adminToModeratorActionDate;

  // Step 3a. Info from admin to intern
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admin_to_intern_status")
  @Fetch(FetchMode.JOIN)
  private Dictionary adminToInternStatus;

  @Column(name = "admin_to_intern_action_date")
  private LocalDate adminToInternActionDate;

  // Step 4 Info from moderator to intern
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "moderator_to_intern_status")
  @Fetch(FetchMode.JOIN)
  private Dictionary moderatorToInternStatus;

  @Column(name = "moderator_to_intern_action_date")
  private LocalDate moderatorToInternActionDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "institute_id", nullable = false)
  private Institute institute;

  public Request() {

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

  public Long getInternId() {
    return internId;
  }

  public void setInternId(Long internId) {
    this.internId = internId;
  }

  public Long getModeratorId() {
    return moderatorId;
  }

  public void setModeratorId(Long moderatorId) {
    this.moderatorId = moderatorId;
  }

  public LocalDate getInternToModeratorActionDate() {
    return internToModeratorActionDate;
  }

  public void setInternToModeratorActionDate(LocalDate internToModeratorActionDate) {
    this.internToModeratorActionDate = internToModeratorActionDate;
  }

  public Dictionary getInternToModeratorStatus() {
    return internToModeratorStatus;
  }

  public void setInternToModeratorStatus(Dictionary internToModeratorStatus) {
    this.internToModeratorStatus = internToModeratorStatus;
  }

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public Dictionary getModeratorToAdminStatus() {
    return moderatorToAdminStatus;
  }

  public void setModeratorToAdminStatus(Dictionary moderatorToAdminStatus) {
    this.moderatorToAdminStatus = moderatorToAdminStatus;
  }

  public LocalDate getModeratorToAdminActionDate() {
    return moderatorToAdminActionDate;
  }

  public void setModeratorToAdminActionDate(LocalDate moderatorToAdminActionDate) {
    this.moderatorToAdminActionDate = moderatorToAdminActionDate;
  }

  public Dictionary getAdminToModeratorStatus() {
    return adminToModeratorStatus;
  }

  public void setAdminToModeratorStatus(Dictionary adminToModeratorStatus) {
    this.adminToModeratorStatus = adminToModeratorStatus;
  }

  public LocalDate getAdminToModeratorActionDate() {
    return adminToModeratorActionDate;
  }

  public void setAdminToModeratorActionDate(LocalDate adminToModeratorActionDate) {
    this.adminToModeratorActionDate = adminToModeratorActionDate;
  }

  public Dictionary getAdminToInternStatus() {
    return adminToInternStatus;
  }

  public void setAdminToInternStatus(Dictionary adminToInternStatus) {
    this.adminToInternStatus = adminToInternStatus;
  }

  public LocalDate getAdminToInternActionDate() {
    return adminToInternActionDate;
  }

  public void setAdminToInternActionDate(LocalDate adminToInternActionDate) {
    this.adminToInternActionDate = adminToInternActionDate;
  }

  public Dictionary getModeratorToInternStatus() {
    return moderatorToInternStatus;
  }

  public void setModeratorToInternStatus(Dictionary moderatorToInternStatus) {
    this.moderatorToInternStatus = moderatorToInternStatus;
  }

  public LocalDate getModeratorToInternActionDate() {
    return moderatorToInternActionDate;
  }

  public void setModeratorToInternActionDate(LocalDate moderatorToInternActionDate) {
    this.moderatorToInternActionDate = moderatorToInternActionDate;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }
}
