package pl.hackyeah.bumboxqueue.error;

public enum ServiceErrorCode {
  CONNECTION_FAILED(1),
  INVALID_TIME_SYNTAX(2),
  PATIENT_ALREADY_EXIST(3),
  INVALID_GENDER_TYPE(4),
  PATIENT_NOT_FOUND(5);

  private int code;


  ServiceErrorCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
