package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;
import pl.hackyeah.bumboxqueue.error.BadRequestException;

@Data
public class FromToDate {
  private HourMinute from;
  private HourMinute to;

  public void validate() throws BadRequestException {
    from.validate();
    to.validate();
  }
}
