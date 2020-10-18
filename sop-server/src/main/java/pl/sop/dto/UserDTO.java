/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dto;

import java.time.LocalDate;

public class UserDTO {

  private Long id;
  private String first_name;
  private String last_name;
  private String username;
  private String phone;
  private String academic_title;
  private LocalDate birthDate;

  public UserDTO() {
  }

  public UserDTO(Long id, String first_name, String last_name, String username, String phone,
      String academic_title, LocalDate birthDate) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.phone = phone;
    this.academic_title = academic_title;
    this.birthDate = birthDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAcademic_title() {
    return academic_title;
  }

  public void setAcademic_title(String academic_title) {
    this.academic_title = academic_title;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}
