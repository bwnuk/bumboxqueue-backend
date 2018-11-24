package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;


@Data
public class VisitEntity {

    @Id
    @Column(nullable = false)
    private int id;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    public String from;

    @Size(min = 2, max = 30)
    @Column(nullable = false)
    public String to;

    @Column(nullable = false)
    @OneToOne
    public DoctorEntity doctor;

    @Column(nullable = false)
    @OneToOne
    public PatientEntity patient;


}
