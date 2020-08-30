package pl.sop.dto;

import java.time.LocalDateTime;

public class TokenDTO {
  private String value;
  private LocalDateTime expirationDate;
  private Long collegeId;
  private Long facultyId;
  private Long instituteId;
  private Long departmentId;
  private Integer remainingUses;
  private Long createdBy;

  public TokenDTO() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public Long getFacultyId() {
    return facultyId;
  }

  public void setFacultyId(Long facultyId) {
    this.facultyId = facultyId;
  }

  public Long getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(Long instituteId) {
    this.instituteId = instituteId;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public Integer getRemainingUses() {
    return remainingUses;
  }

  public void setRemainingUses(Integer remainingUses) {
    this.remainingUses = remainingUses;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }
}
