package pl.hackyeah.bumboxqueue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String role;
  private Long patientId;

  public UserEntity() {
    role = RoleUser.USER.getRole();
  }

  public UserEntity(String username, String password, Long patientId) {
    this();
    this.username = username;
    this.password = password;
    this.patientId = patientId;
  }
}