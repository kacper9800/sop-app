package pl.sop.dto;

import java.time.LocalDateTime;

public class DirectionDTO {
  private String name;
  private String description;
  private Integer studyMode;
  private Long amountOfStudents;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Boolean active;
  private Boolean removed;

  public DirectionDTO() {
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

  public Integer getStudyMode() {
    return studyMode;
  }

  public void setStudyMode(Integer studyMode) {
    this.studyMode = studyMode;
  }

  public Long getAmountOfStudents() {
    return amountOfStudents;
  }

  public void setAmountOfStudents(Long amountOfStudents) {
    this.amountOfStudents = amountOfStudents;
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
}
