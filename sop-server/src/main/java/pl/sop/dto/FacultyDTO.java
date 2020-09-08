package pl.sop.dto;

import java.util.List;

public class FacultyDTO {
  private Long id;
  private String name;
  private List<InstituteDTO> institutes;

  public FacultyDTO() {
  }

  public FacultyDTO(Long id, String name, List<InstituteDTO> institutes) {
    this.id = id;
    this.name = name;
    this.institutes = institutes;
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

  public List<InstituteDTO> getInstitutes() { return institutes; }

  public void setInstitutes(List<InstituteDTO> institutes) { this.institutes = institutes; }
}
