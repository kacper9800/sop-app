package pl.sop.dto;

import java.util.List;

public class CollegeStructureDTO {
  private Long id;
  private String collegeName;
  private List<FacultyDTO> faculties;

  public CollegeStructureDTO() {
  }

  public CollegeStructureDTO(Long id, String collegeName, List<FacultyDTO> faculties) {
    this.id = id;
    this.collegeName = collegeName;
    this.faculties = faculties;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public List<FacultyDTO> getFaculties() {
    return faculties;
  }

  public void setFaculties(List<FacultyDTO> faculties) {
    this.faculties = faculties;
  }
}
