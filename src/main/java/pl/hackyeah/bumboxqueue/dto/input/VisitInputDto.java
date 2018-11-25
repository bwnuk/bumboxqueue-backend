package pl.hackyeah.bumboxqueue.dto.input;

import lombok.Data;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VisitInputDto {
    private Long idPatient;
    private Long idDoctor;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    public void validate(){
        if(startTime.isAfter(endTime)){
            throw  new BadRequestException(String.format("Wrong start time"), ServiceErrorCode.WRONG_TIME);
        }
    }
}
