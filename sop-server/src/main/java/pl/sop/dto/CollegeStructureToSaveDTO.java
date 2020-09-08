package pl.sop.dto;

public class CollegeStructureToSaveDTO {
  private Long collegeId;
  private Long parentId;
  private Integer level; // 0 - College, 1 - Faculty, 2 - Institute, 3 -Department
  private String structureName;

  public CollegeStructureToSaveDTO() {
  }

  public CollegeStructureToSaveDTO(Long collegeId, Long parentId, Integer level,
      String structureName) {
    this.collegeId = collegeId;
    this.parentId = parentId;
    this.level = level;
    this.structureName = structureName;
  }

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getStructureName() {
    return structureName;
  }

  public void setStructureName(String structureName) {
    this.structureName = structureName;
  }
}
