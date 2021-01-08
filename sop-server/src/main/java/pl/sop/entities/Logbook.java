/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.Institute;

@Entity
@Table(name = "logbooks")
public class Logbook extends BasicEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "intern_id")
  private User intern;

  @ManyToOne
  @JoinColumn(name = "institute_id")
  private Institute institute;

  @ManyToOne
  @JoinColumn(name = "college_id")
  private College college;

  @JsonIgnore
  @OneToMany(mappedBy = "logbook", orphanRemoval = true)
  private List<LogbookPost> logbookPosts;

  @OneToOne
  @JoinColumn(name = "internship_id")
  private Internship internship;

  public Logbook() {
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

  public User getIntern() {
    return intern;
  }

  public void setIntern(User intern) {
    this.intern = intern;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }

  public List<LogbookPost> getLogbookPosts() {
    return logbookPosts;
  }

  public void setLogbookPosts(List<LogbookPost> logbookPosts) {
    this.logbookPosts = logbookPosts;
  }

  public Internship getInternship() {
    return internship;
  }

  public void setInternship(Internship internship) {
    this.internship = internship;
  }
}
