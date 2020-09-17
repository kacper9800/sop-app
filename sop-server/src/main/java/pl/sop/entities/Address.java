package pl.sop.entities;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")
public class Address extends BasicEntity{

  private String city;
  private String street;
  private String number;
  private String postCode;

  @OneToOne
  @MapsId
  private Company company;

  public Address() {
  }

  public Address(String city, String street, String number, String postCode,
      Company company) {
    this.city = city;
    this.street = street;
    this.number = number;
    this.postCode = postCode;
    this.company = company;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
