package pl.hackyeah.bumboxqueue.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "patient")
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
  private String gender;

  @Column
  @Size(min = 11)
  private String pesel;
}
