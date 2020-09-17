package pl.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BasicEntity {

  private String name;

  @JsonIgnore
  @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
  private Address address;

  @JsonIgnore
  @OneToMany(mappedBy = "companies", orphanRemoval = true)
  private List<User> users;

  public Company() {
  }

  public Company(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
