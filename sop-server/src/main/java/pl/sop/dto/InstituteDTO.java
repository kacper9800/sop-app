package pl.sop.dto;

import java.util.List;

public class InstituteDTO {
  private Long id;
  private String name;
  private List<DepartmentDTO> departmentDTO;

  public InstituteDTO() {
  }

  public InstituteDTO(Long id, String name, List<DepartmentDTO> departmentDTO) {
    this.id = id;
    this.name = name;
    this.departmentDTO = departmentDTO;
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

  public List<DepartmentDTO> getDepartmentDTO() {
    return departmentDTO;
  }

  public void setDepartmentDTO(List<DepartmentDTO> departmentDTO) {
    this.departmentDTO = departmentDTO;
  }
}
