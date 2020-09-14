package pl.sop.organizationStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.sop.entities.BasicEntity;

import javax.persistence.Entity;
import java.io.Serializable;
import pl.sop.entities.User;
import pl.sop.entities.Token;

@Entity
@Table(name = "faculties")
public class Faculty extends BasicEntity implements Serializable {

  @Column(name = "name", nullable = false)
  private String name;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  private Set<User> users;

  @JsonIgnore
  @OneToMany(mappedBy="faculty")
  private Set<Token> tokens;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  private College college;

  @JsonIgnore
  @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Institute> institutes;

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

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public Set<Token> getTokens() { return tokens; }

  public void setTokens(Set<Token> tokens) { this.tokens = tokens; }

  public College getCollege() { return college; }

  public void setCollege(College college) { this.college = college; }

  public Set<Institute> getInstitutes() { return institutes; }

  public void setInstitutes(Set<Institute> institutes) { this.institutes = institutes; }
}
