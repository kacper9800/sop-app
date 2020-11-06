package pl.sop.dto;

public class DictionaryDTO {

  private String name;
  private String value;
  private String dictionaryType;
  private Boolean active;
  private Boolean removed;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDictionaryType() {
    return dictionaryType;
  }

  public void setDictionaryType(String dictionaryType) {
    this.dictionaryType = dictionaryType;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }
}
