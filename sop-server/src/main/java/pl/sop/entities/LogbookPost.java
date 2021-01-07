package pl.sop.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logbook_posts")
public class LogbookPost extends BasicEntity {

  @Column(name = "description")
  private String description;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "hours")
  private Integer hours;

  @ManyToOne(fetch = FetchType.EAGER)
  private Logbook logbook;

  public LogbookPost() {
  }

  public LogbookPost(String description, LocalDate date, Integer hours,
      Logbook logbook) {
    this.description = description;
    this.date = date;
    this.hours = hours;
    this.logbook = logbook;
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

  public Integer getHours() {
    return hours;
  }

  public void setHours(Integer hours) {
    this.hours = hours;
  }

  public Logbook getLogbook() {
    return logbook;
  }

  public void setLogbook(Logbook logbook) {
    this.logbook = logbook;
  }
}
