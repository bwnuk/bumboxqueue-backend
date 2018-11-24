package pl.hackyeah.bumboxqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;

@Repository
public interface DoctorRepository extends JpaRepository<PatientEntity, Long> {

}
