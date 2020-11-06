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

  @Column(name = "START_EXPIRATION_DATE")
  private LocalDateTime startExpirationDate;

  @Column(name = "END_EXPIRATION_dATE")
  private LocalDateTime endExpirationDate;

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

  public LocalDateTime getStartExpirationDate() {
    return startExpirationDate;
  }

  public void setStartExpirationDate(LocalDateTime startExpirationDate) {
    this.startExpirationDate = startExpirationDate;
  }

  public LocalDateTime getEndExpirationDate() {
    return endExpirationDate;
  }

  public void setEndExpirationDate(LocalDateTime endExpirationDate) {
    this.endExpirationDate = endExpirationDate;
  }
}
