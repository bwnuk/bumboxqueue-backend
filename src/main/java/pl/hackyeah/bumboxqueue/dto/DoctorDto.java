package pl.hackyeah.bumboxqueue.dto;


import lombok.Data;
import pl.hackyeah.bumboxqueue.error.BadRequestException;

import javax.validation.constraints.Null;

@Data
public class DoctorDto {
    private String name;
    private String surname;
    public String specialization;


    public void validate() throws Exception {

    }
}
