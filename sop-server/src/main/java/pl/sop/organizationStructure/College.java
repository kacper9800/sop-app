/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.organizationStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.sop.entities.BasicEntity;
import pl.sop.entities.Token;
import pl.sop.entities.User;

@Entity
@Table(name = "Colleges")
public class College extends BasicEntity implements Serializable {

  @Column(name = "name", nullable = false)
  private String name;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_colleges",
      joinColumns = @JoinColumn(name = "college_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;

  @JsonIgnore
  @OneToMany(mappedBy = "college", orphanRemoval = true)
  private List<Token> tokens;

  @JsonIgnore
  @OneToMany(mappedBy = "college", cascade = CascadeType.MERGE, orphanRemoval = true)
  private List<Faculty> faculties;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<Token> getTokens() { return tokens; }

  public void setTokens(List<Token> tokens) { this.tokens = tokens; }

  public List<Faculty> getFaculties() { return faculties; }

  public void setFaculties(List<Faculty> faculties) { this.faculties = faculties; }

}
