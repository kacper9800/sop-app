package pl.sop.dto;

public class CollegeDTO {
  private Long id;
  private String name;
  private Boolean active;
  private Boolean deleted;

  public CollegeDTO() {
  }

  public CollegeDTO(Long id, String name, Boolean active, Boolean deleted) {
    this.id = id;
    this.name = name;
    this.active = active;
    this.deleted = deleted;
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
}
