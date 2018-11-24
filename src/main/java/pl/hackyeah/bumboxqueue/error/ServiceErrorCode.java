package pl.hackyeah.bumboxqueue.error;

public enum ServiceErrorCode {
  CONNECTION_FAILED(1),
  INVALID_TIME_SYNTAX(2),
  NOT_FOUND(3);

  private int code;


  ServiceErrorCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
