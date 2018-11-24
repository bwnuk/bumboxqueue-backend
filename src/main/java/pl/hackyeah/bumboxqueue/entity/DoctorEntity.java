package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.validation.constraints.Size;


@Data
public class DoctorEntity {

    @Id
    @Column(nullable = false)
    private int id;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    public String name;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    public String surname;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    public String specialization;


}





