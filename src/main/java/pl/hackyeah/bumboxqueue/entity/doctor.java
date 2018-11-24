package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Id;


@Data
public class doctor {
    @Id
    private int id;
    public String name;
    public String surname;
    public String specialization;


}





