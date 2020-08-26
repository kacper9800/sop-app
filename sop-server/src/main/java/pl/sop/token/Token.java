package pl.sop.token;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import pl.sop.entities.BasicEntity;
import pl.sop.entities.User;

@Entity
@Table(name = "tokens")
public class Token extends BasicEntity {

  @Column(name = "value")
  private String value;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;

  @Column(name = "college_id")
  private Long collegeId;

  @Column(name = "faculty_id")
  private Long facultyId;

  @Column(name = "institute_id")
  private Long instituteId;

  @Column(name = "department_id")
  private Long departmentId;

  @Column(name = "remaining_uses")
  private Integer remainingUses;

  @Column(name = "created_by_id")
  private User createdBy;

  public Token(String value, LocalDateTime expirationDate, Long collegeId, Long facultyId,
      Long instituteId, Long departmentId, Integer remainingUses) {
    this.value = value;
    this.expirationDate = expirationDate;
    this.collegeId = collegeId;
    this.facultyId = facultyId;
    this.instituteId = instituteId;
    this.departmentId = departmentId;
    this.remainingUses = remainingUses;
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

  public User getCreatedBy() { return createdBy; }

  public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}
