package pl.hackyeah.bumboxqueue.dto;


import lombok.Data;

import javax.validation.constraints.Null;

@Data
public class DoctorDto {
    private String name;
    private String surname;
    public String specialization;


    public void validate() throws Exception {
        if(name.length() == 0)
        {
            throw new NullPointerException("No name parameter");
        }
        if(name.length() > 30)
        {
            throw new Exception("Name too long");
        }
        if(surname.length() == 0)
        {
            throw new NullPointerException("No surname parameter");
        }
        if(surname.length() > 30)
        {
            throw new Exception("Surname too long");
        }
        if(specialization.length() == 0)
        {
            throw new NullPointerException("No specialization parameter");
        }
        if(specialization.length() > 30)
        {
            throw new Exception("specialization too long");
        }

    }
}
