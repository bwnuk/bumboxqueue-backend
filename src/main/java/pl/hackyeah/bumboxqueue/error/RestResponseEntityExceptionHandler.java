package pl.hackyeah.bumboxqueue.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private ErrorDto createError(String message, ServiceErrorCode serviceErrorCode) {
    return new ErrorDto(message, serviceErrorCode);
  }

  @ExceptionHandler(value = {ServiceException.class})
  protected ResponseEntity<Object> handlerServiceException(RuntimeException rte,
      WebRequest request) {
    ServiceException ex = (ServiceException) rte;
    ErrorDto errorDto = createError(ex.getMessage(), ex.getServiceErrorCode());
    log.warn(errorDto.toString());
    return handleExceptionInternal(rte, errorDto, new HttpHeaders(), ex.getHttpStatus(),
        request);
  }
}
