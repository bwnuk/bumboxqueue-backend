package pl.hackyeah.bumboxqueue.entity;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="history")
public class HistoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String user;
  private String action;
  private LocalTime dateTime;

  public HistoryEntity(String user, String action) {
    dateTime = LocalTime.now();
  }
}
