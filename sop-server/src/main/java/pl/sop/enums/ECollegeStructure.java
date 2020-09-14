package pl.sop.enums;

public enum ECollegeStructure {
  COLLEGE(0), // UCZELNIA
  FACULTY(1), // WYDZIAŁ
  INSTITUTE(2), // INSTYTUT
  DEPARTMENT(3); // KATEDRA

  Integer value;

  private ECollegeStructure(Integer value) {
    this.value = value;
  }
}
