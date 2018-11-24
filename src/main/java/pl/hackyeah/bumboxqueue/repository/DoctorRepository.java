package pl.hackyeah.bumboxqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.entity.DoctorEntity;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByPesel(String pesel);

//    List<DoctorEntity> findAllBySpecializations(String specialization);

}
