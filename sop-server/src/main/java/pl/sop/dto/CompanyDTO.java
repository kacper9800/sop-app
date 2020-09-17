package pl.sop.dto;

import pl.sop.entities.Address;

public class CompanyDTO {
  private String name;
  private AddressDTO addressDTO;

  public CompanyDTO() {
  }

  public CompanyDTO(String name, AddressDTO addressDTO) {
    this.name = name;
    this.addressDTO = addressDTO;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AddressDTO getAddressDTO() {
    return addressDTO;
  }

  public void setAddressDTO(AddressDTO addressDTO) {
    this.addressDTO = addressDTO;
  }
}
