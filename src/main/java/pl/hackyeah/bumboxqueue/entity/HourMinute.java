package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;

@Data
public class HourMinute {
  private int hour;
  private int minute;

  public void validate() throws BadRequestException {
    if(hour < 0 || hour > 24 || minute < 0 || minute > 59) {
      throw new BadRequestException("Invalid hour minute syntax", ServiceErrorCode.INVALID_TIME_SYNTAX);
    }
  }
}
