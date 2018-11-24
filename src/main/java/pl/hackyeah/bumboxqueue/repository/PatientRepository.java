package pl.hackyeah.bumboxqueue.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

  Optional<PatientEntity> findByPesel(String pesel);
}
