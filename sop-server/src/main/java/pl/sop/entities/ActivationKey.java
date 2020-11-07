package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.Institute;

@Entity
@Table(name = "activation_keys")
public class ActivationKey extends BasicEntity {

  @JsonIgnore
  @Column(name = "value")
  private String value;

  @JsonIgnore
  @Column(name = "start_expiration_date")
  private LocalDateTime startExpirationDate;

  @JsonIgnore
  @Column(name = "end_expiration_date")
  private LocalDateTime endExpirationDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "college_id", nullable = false, updatable = false)
  private College college;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id", nullable = false)
  private Faculty faculty;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "institute_id", nullable = false)
  private Institute institute;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id", nullable = false)
  private Department department;

  @JsonIgnore
  @Column(name = "remaining_uses")
  private Integer numberOfUses;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "direction_id")
  private Direction direction;


//  @JsonIgnore
//  @Column

//  ToDO
//  @JsonIgnore
//  @Column(name = "creator_id")
//  private User createdBy;


  public ActivationKey() {
  }

  public ActivationKey(String value, LocalDateTime startExpirationDate,
      LocalDateTime endExpirationDate, College college, Faculty faculty,
      Institute institute, Department department, Integer numberOfUses) {
    this.value = value;
    this.startExpirationDate = startExpirationDate;
    this.endExpirationDate = endExpirationDate;
    this.college = college;
    this.faculty = faculty;
    this.institute = institute;
    this.department = department;
    this.numberOfUses = numberOfUses;
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

  public void setStartExpirationDate(LocalDateTime startExpirationDate) {
    this.startExpirationDate = startExpirationDate;
  }

  public LocalDateTime getEndExpirationDate() {
    return endExpirationDate;
  }

  public void setEndExpirationDate(LocalDateTime endExpirationDate) {
    this.endExpirationDate = endExpirationDate;
  }

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Integer getNumberOfUses() {
    return numberOfUses;
  }

  public void setNumberOfUses(Integer numberOfUses) {
    this.numberOfUses = numberOfUses;
  }

//  public User getCreatedBy() { return createdBy; }
//
//  public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }


  public Direction getDirection() { return direction; }

  public void setDirection(Direction direction) { this.direction = direction; }
}
