package pl.hackyeah.bumboxqueue.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

  private String message;
  private ServiceErrorCode serviceErrorCode;
}
