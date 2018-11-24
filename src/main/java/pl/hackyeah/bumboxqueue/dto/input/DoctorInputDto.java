package pl.hackyeah.bumboxqueue.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class DoctorInputDto {

    private String firstName;
    private String lastName;
    private List<String> specialization;
    private String pesel;
}
