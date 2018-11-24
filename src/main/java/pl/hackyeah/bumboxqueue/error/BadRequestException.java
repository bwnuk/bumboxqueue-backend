package pl.hackyeah.bumboxqueue.error;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException {

  public BadRequestException(String message, ServiceErrorCode serviceErrorCode) {
    super(message, HttpStatus.BAD_REQUEST, serviceErrorCode);
  }
}