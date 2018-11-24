package pl.hackyeah.bumboxqueue.repository;

import org.graalvm.compiler.lir.amd64.AMD64BinaryConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.hackyeah.bumboxqueue.entity.VisitEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
    Optional<VisitEntity> findByIdPatient(Long idPatient);
    Optional<VisitEntity> findByIdDctor(Long idDoctor);
    Optional<VisitEntity> findByStartTime(LocalTime startTime, LocalDate date);
    Optional<VisitEntity> findByEndTime(LocalTime endTime, LocalDate date);
}
