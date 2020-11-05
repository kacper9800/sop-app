package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.sop.organizationStructure.Institute;

@Entity
@Table(name = "DIRECTIONS")
public class Direction extends BasicEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "amount_of_students")
  private Long amountOfStudents;

  @Column(name = "study_mode")
  private Integer studyMode;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "institute_id", nullable = false)
  private Institute institute;

  public Direction() {
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

  public Long getAmountOfStudents() {
    return amountOfStudents;
  }

  public void setAmountOfStudents(Long amountOfStudents) {
    this.amountOfStudents = amountOfStudents;
  }

  public Integer getStudyMode() {
    return studyMode;
  }

  public void setStudyMode(Integer studyMode) {
    this.studyMode = studyMode;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }
}
