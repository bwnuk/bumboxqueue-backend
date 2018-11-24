package pl.hackyeah.bumboxqueue.entity;

public enum GenderType {
  WOMAN("WOMAN"),
  MAN("MAN");

  private String gender;

  GenderType(String gender) {
    this.gender = gender;
  }

  public String getGender() {
    return gender;
  }
}
