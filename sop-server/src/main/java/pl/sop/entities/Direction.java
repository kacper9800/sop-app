package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Direction extends BasicEntity{

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "amount_of_students")
  private Long amountOfStudents;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "study_mode")
  @Fetch(FetchMode.JOIN)
  private Dictionary studyMode;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

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

  public void setAmountOfStudents(Long amountOfStudents) { this.amountOfStudents = amountOfStudents; }

  public Dictionary getStudyMode() {
    return studyMode;
  }

  public void setStudyMode(Dictionary studyMode) {
    this.studyMode = studyMode;
  }

  public LocalDate getStartDate() { return startDate; }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }


}
