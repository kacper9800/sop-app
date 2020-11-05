/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.dto;

import java.time.LocalDate;
import liquibase.pro.packaged.B;

public class UserDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String phone;
  private String academicTitle;
  private LocalDate birthDate;
  private Boolean active;
  private Boolean removed;

  public UserDTO() {
  }

  public UserDTO(Long id, String firstName, String lastName, String username, String phone,
      String academicTitle, LocalDate birthDate, Boolean active, Boolean removed) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.phone = phone;
    this.academicTitle = academicTitle;
    this.birthDate = birthDate;
    this.active = active;
    this.removed = removed;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public String getAcademicTitle() {
    return academicTitle;
  }

  public void setAcademicTitle(String academicTitle) {
    this.academicTitle = academicTitle;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }
}
