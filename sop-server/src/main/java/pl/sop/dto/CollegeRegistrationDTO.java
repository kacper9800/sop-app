package pl.sop.dto;

public class CollegeRegistrationDTO {

  private String token;
  private Long collegeId;
  private String email;
  private String password;

  public String getToken() { return token; }

  public void setToken(String token) { this.token = token; }

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}