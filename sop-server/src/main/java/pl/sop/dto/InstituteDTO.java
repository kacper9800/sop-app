package pl.sop.dto;

import java.util.List;

public class InstituteDTO {

  private Long id;
  private String name;
  private List<DepartmentDTO> departments;

  public InstituteDTO() {
  }

  public InstituteDTO(Long id, String name, List<DepartmentDTO> departments) {
    this.id = id;
    this.name = name;
    this.departments = departments;
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

  public List<DepartmentDTO> getDepartments() {
    return departments;
  }

  public void setDepartments(List<DepartmentDTO> departments) {
    this.departments = departments;
  }
}
