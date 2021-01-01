package pl.sop.dto;

import java.util.List;

public class LogbookDTO {
  private Long id;
  private Long internId;
  private Long internName;
  private List<LogbookPostDTO> posts;

  public LogbookDTO(Long id, Long internId, Long internName,
      List<LogbookPostDTO> posts) {
    this.id = id;
    this.internId = internId;
    this.internName = internName;
    this.posts = posts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getInternId() {
    return internId;
  }

  public void setInternId(Long internId) {
    this.internId = internId;
  }

  public Long getInternName() {
    return internName;
  }

  public void setInternName(Long internName) {
    this.internName = internName;
  }

  public List<LogbookPostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<LogbookPostDTO> posts) {
    this.posts = posts;
  }
}
