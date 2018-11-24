package pl.hackyeah.bumboxqueue.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1)
    private String lastName;

    @Column(nullable = false)
    @Size(min = 1)
    private String sex;

    @Column
    @Size(min = 1)
    private long pesel;

    public PatientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }
}
