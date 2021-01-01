package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import liquibase.pro.packaged.C;

@Entity
@Table(name = "companies")
public class Company extends BasicEntity {

  private String name;

  private String description;

  @JsonIgnore
  @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
  private Address address;

  @JsonIgnore
  @OneToMany(mappedBy = "companies", orphanRemoval = true)
  private List<User> users;

  @Column(name = "nip")
  private String nip;

  public Company() {
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }
}
