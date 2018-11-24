package pl.hackyeah.bumboxqueue.dto.output;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VisitOutputDto {
    private Long idPatient;
    private Long idDoctor;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
}
