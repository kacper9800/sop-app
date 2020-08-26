package pl.sop.organizationStructure;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import pl.sop.entities.BasicEntity;

import javax.persistence.Entity;
import java.io.Serializable;
import pl.sop.entities.User;

@Entity
@Table(name = "faculties")
public class Faculty extends BasicEntity implements Serializable {

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<User> users;

  //ToDo Create college_id field

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
}
