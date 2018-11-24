package pl.hackyeah.bumboxqueue.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 20)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, max = 20)
    private String lastName;

    @Column(nullable = false)
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private List<SpecializationEntity> specializations = new ArrayList<>();

    @Column(unique = true, nullable = false)
    @Size(min = 11, max = 11)
    private String pesel;


}
