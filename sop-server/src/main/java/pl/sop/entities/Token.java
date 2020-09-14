package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import pl.sop.entities.BasicEntity;
import pl.sop.entities.User;
import pl.sop.organizationStructure.College;
import pl.sop.organizationStructure.Department;
import pl.sop.organizationStructure.Faculty;
import pl.sop.organizationStructure.Institute;

@Entity
@Table(name = "tokens")
public class Token extends BasicEntity {

  @JsonIgnore
  @Column(name = "value")
  private String value;

  @JsonIgnore
  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "college_id",insertable = false, nullable = false, updatable = false)
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
  private Integer remainingUses;
//  ToDO
//  @JsonIgnore
//  @Column(name = "creator_id")
//  private User createdBy;

  public Token() {
  }

  public Token(String value, LocalDateTime expirationDate, College college, Faculty faculty,
      Institute institute, Department department, Integer remainingUses) {
    this.value = value;
    this.expirationDate = expirationDate;
    this.college = college;
    this.faculty = faculty;
    this.institute = institute;
    this.department = department;
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

  public void setExpirationDate(LocalDateTime expirationDate) { this.expirationDate = expirationDate; }

  public College getCollege() { return college; }

  public void setCollege(College college) { this.college = college; }

  public Faculty getFaculty() { return faculty; }

  public void setFaculty(Faculty faculty) { this.faculty = faculty; }

  public Institute getInstitute() { return institute; }

  public void setInstitute(Institute institute) { this.institute = institute; }

  public Department getDepartment() { return department; }

  public void setDepartment(Department department) { this.department = department; }

  public Integer getRemainingUses() {
    return remainingUses;
  }

  public void setRemainingUses(Integer remainingUses) {
    this.remainingUses = remainingUses;
  }

//  public User getCreatedBy() { return createdBy; }
//
//  public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}
