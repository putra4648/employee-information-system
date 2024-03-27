package id.pradana.ems.model;

public enum Gender {
  MALE('M'),
  FEMALE('F');

  private char code;

  private Gender(char code) {
    this.code = code;
  }

  public char getCode() {
    return code;
  }
}
