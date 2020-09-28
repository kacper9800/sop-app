package pl.sop.dto;

public class CollegeDTO {
  private Long id;
  private String name;

  public CollegeDTO() {
  }

  public CollegeDTO(Long id, String name) {
    this.id = id;
    this.name = name;
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
}
