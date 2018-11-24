package pl.hackyeah.bumboxqueue.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
  private final HttpStatus httpStatus;
  private final ServiceErrorCode serviceErrorCode;

  public ServiceException(String message, HttpStatus httpStatus,
      ServiceErrorCode serviceErrorCode) {
    super(message);
    this.httpStatus = httpStatus;
    this.serviceErrorCode = serviceErrorCode;
  }
}
