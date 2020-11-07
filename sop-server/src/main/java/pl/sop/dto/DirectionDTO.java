package pl.sop.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DirectionDTO {
  private Long id;
  private String name;
  private String description;
  private String studyMode;
  private Long instituteId;
  private String instituteName;
  private String facultyName;
  private Long amountOfStudents;
  private LocalDate startExpirationDate;
  private LocalDate endExpirationDate;
  private Boolean active;
  private Boolean removed;

  public DirectionDTO() {
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

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

  public String getStudyMode() {
    return studyMode;
  }

  public void setStudyMode(String studyMode) {
    this.studyMode = studyMode;
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

  public String getFacultyName() {
    return facultyName;
  }

  public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
  }

  public Long getAmountOfStudents() {
    return amountOfStudents;
  }

  public void setAmountOfStudents(Long amountOfStudents) {
    this.amountOfStudents = amountOfStudents;
  }

  public LocalDate getStartExpirationDate() {
    return startExpirationDate;
  }

  public void setStartExpirationDate(LocalDate startExpirationDate) {
    this.startExpirationDate = startExpirationDate;
  }

  public LocalDate getEndExpirationDate() {
    return endExpirationDate;
  }

  public void setEndExpirationDate(LocalDate endExpirationDate) {
    this.endExpirationDate = endExpirationDate;
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
