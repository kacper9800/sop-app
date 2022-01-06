package pl.sop.dto;

import java.util.List;

public class LogbookDTO {

  private Long id;
  private String name;
  private String description;
  private Long internId;
  private String internName;
  private Long instituteId;
  private String instituteName;
  private Long collegeId;
  private String collegeName;
  private Long companyId;
  private String companyName;
  private String position;

  private Integer actualAmountOfHours;
  private Integer amountOfHours;

  private List<LogbookPostDTO> posts;
  private Boolean active;

  public LogbookDTO() {
  }

  public LogbookDTO(Long id, String name, String description, Long internId,
      String internName, Long instituteId, String instituteName, Long collegeId,
      String collegeName, Long companyId, String companyName, String position,
      Integer actualAmountOfHours, Integer amountOfHours,
      List<LogbookPostDTO> posts, Boolean active) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.internId = internId;
    this.internName = internName;
    this.instituteId = instituteId;
    this.instituteName = instituteName;
    this.collegeId = collegeId;
    this.collegeName = collegeName;
    this.companyId = companyId;
    this.companyName = companyName;
    this.position = position;
    this.actualAmountOfHours = actualAmountOfHours;
    this.amountOfHours = amountOfHours;
    this.posts = posts;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getInternId() {
    return internId;
  }

  public void setInternId(Long internId) {
    this.internId = internId;
  }

  public String getInternName() {
    return internName;
  }

  public void setInternName(String internName) {
    this.internName = internName;
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

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Integer getActualAmountOfHours() {
    return actualAmountOfHours;
  }

  public void setActualAmountOfHours(Integer actualAmountOfHours) {
    this.actualAmountOfHours = actualAmountOfHours;
  }

  public Integer getAmountOfHours() {
    return amountOfHours;
  }

  public void setAmountOfHours(Integer amountOfHours) {
    this.amountOfHours = amountOfHours;
  }

  public List<LogbookPostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<LogbookPostDTO> posts) {
    this.posts = posts;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
