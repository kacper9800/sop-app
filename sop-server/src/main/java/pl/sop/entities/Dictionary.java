package pl.sop.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import pl.sop.enums.EDictionaryType;

@Entity
@Table(name = "DICTIONARIES")
public class Dictionary extends BasicEntity{

  @Column(name = "DICTIONARY_TYPE")
  @Enumerated(EnumType.STRING)
  private EDictionaryType dictionaryType;

  @Column(name = "NAME")
  private String name;

  @Column(name = "VALUE")
  private String value;

  @Column(name = "ACTIVE_START")
  private LocalDateTime activeStart;

  @Column(name = "ACTIVE_END")
  private LocalDateTime activeEnd;

  public Dictionary() {
  }

  public EDictionaryType getDictionaryType() {
    return dictionaryType;
  }

  public void setDictionaryType(EDictionaryType dictionaryType) {
    this.dictionaryType = dictionaryType;
  }

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

  public LocalDateTime getActiveStart() {
    return activeStart;
  }

  public void setActiveStart(LocalDateTime activeStart) {
    this.activeStart = activeStart;
  }

  public LocalDateTime getActiveEnd() {
    return activeEnd;
  }

  public void setActiveEnd(LocalDateTime activeEnd) {
    this.activeEnd = activeEnd;
  }
}
