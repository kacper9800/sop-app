package pl.sop.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ActivationKeyDTO {

  private Long id;
  private String value;
  private LocalDateTime startExpirationDate;
  private LocalDateTime endExpirationDate;
  private String role;
  private Long directionId;
  private String directionName;
  private Long collegeId;
  private String collegeName;
  private Long facultyId;
  private String facultyName;
  private Long instituteId;
  private String instituteName;
  private Long departmentId;
  private String departmentName;
  private Integer numberOfUses;
  private Long createdById;
  private String createdByName;
  private Boolean active;
  private Boolean deleted;

  public ActivationKeyDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public LocalDateTime getStartExpirationDate() {
    return startExpirationDate;
  }

  public void setStartExpirationDate(LocalDateTime startExpirationDate) { this.startExpirationDate = startExpirationDate; }

  public LocalDateTime getEndExpirationDate() {
    return endExpirationDate;
  }

  public void setEndExpirationDate(LocalDateTime endExpirationDate) { this.endExpirationDate = endExpirationDate; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }

  public Long getDirectionId() { return directionId; }

  public void setDirectionId(Long directionId) { this.directionId = directionId; }

  public String getDirectionName() { return directionName; }

  public void setDirectionName(String directionName) { this.directionName = directionName; }

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

  public Long getFacultyId() {
    return facultyId;
  }

  public void setFacultyId(Long facultyId) {
    this.facultyId = facultyId;
  }

  public String getFacultyName() {
    return facultyName;
  }

  public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
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

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getNumberOfUses() {
    return numberOfUses;
  }

  public void setNumberOfUses(Integer numberOfUses) {
    this.numberOfUses = numberOfUses;
  }

  public Long getCreatedById() {
    return createdById;
  }

  public void setCreatedById(Long createdById) {
    this.createdById = createdById;
  }

  public String getCreatedByName() {
    return createdByName;
  }

  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
}
