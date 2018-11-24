package pl.hackyeah.bumboxqueue.dto.input;

import lombok.Data;
import pl.hackyeah.bumboxqueue.entity.GenderType;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;


@Data
public class PatientInputDto {
    private String firstName;
    private String lastName;
    private String pesel;
    private String gender;

    public void validate() {
        if(!gender.equals(GenderType.MAN.getGender()) && !gender.equals(GenderType.WOMAN.getGender())) {
            throw new BadRequestException(String.format("Gender must be type %s or %s",
                    GenderType.MAN.getGender(), GenderType.WOMAN.getGender()), ServiceErrorCode.INVALID_GENDER_TYPE);
        }
    }
}
