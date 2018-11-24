package pl.hackyeah.bumboxqueue.dto;


import com.sun.jmx.snmp.Timestamp;
import lombok.Data;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;

import java.time.LocalTime;

@Data
public class VisitDto {
    public LocalTime visitStart;
    public LocalTime visitEnd;
    public String doctorPesel;
    public String patientPesel;


    public void validate() throws BadRequestException {
        if(visitStart.isAfter(visitEnd))
        {
            throw new BadRequestException("Started visit cannot be before ended visit", ServiceErrorCode.INVALID_TIME_SYNTAX);
        }
    }
}
