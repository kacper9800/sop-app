package pl.sop.dto;

public class UserModeratorDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String academicTitle;
  private Long instituteId;
  private String instituteName;

  public UserModeratorDTO() {
  }

  public UserModeratorDTO(Long id, String firstName, String lastName, String academicTitle,
      Long instituteId, String instituteName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.academicTitle = academicTitle;
    this.instituteId = instituteId;
    this.instituteName = instituteName;
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

  public String getAcademicTitle() {
    return academicTitle;
  }

  public void setAcademicTitle(String academicTitle) {
    this.academicTitle = academicTitle;
  }

  public Long getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(Long instituteId) {
    this.instituteId = instituteId;
  }

  public String getInstituteName() {
    return instituteName;
  }

  public void setInstituteName(String instituteName) {
    this.instituteName = instituteName;
  }
}
