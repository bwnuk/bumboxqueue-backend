package pl.hackyeah.bumboxqueue.dto.output;

import lombok.Data;

@Data
public class PatientOutputDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String gender;
}
