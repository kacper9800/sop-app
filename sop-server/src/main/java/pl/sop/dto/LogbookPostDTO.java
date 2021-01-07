package pl.sop.dto;

import java.time.LocalDate;

public class LogbookPostDTO {

  private Long id;
  private String description;
  private LocalDate date;
  private Integer amountOfHours;
  private Long logbookId;

  public LogbookPostDTO() {
  }

  public LogbookPostDTO(Long id, String description, LocalDate date, Integer amountOfHours,
      Long logbookId) {
    this.id = id;
    this.description = description;
    this.date = date;
    this.amountOfHours = amountOfHours;
    this.logbookId = logbookId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Integer getAmountOfHours() {
    return amountOfHours;
  }

  public void setAmountOfHours(Integer amountOfHours) {
    this.amountOfHours = amountOfHours;
  }

  public Long getLogbookId() {
    return logbookId;
  }

  public void setLogbookId(Long logbookId) {
    this.logbookId = logbookId;
  }
}
