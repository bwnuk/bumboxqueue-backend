package pl.hackyeah.bumboxqueue.repository;

import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.dto.DoctorDto;
import pl.hackyeah.bumboxqueue.dto.VisitDto;
import pl.hackyeah.bumboxqueue.entity.DoctorEntity;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;
import pl.hackyeah.bumboxqueue.service.VisitService;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface VisitRepository {
    Optional<VisitDto> findByDoctor(DoctorEntity doctor);
    Optional<VisitDto> findByPatient(PatientEntity patient);
    Optional<VisitDto> findByDate(Timestamp date);
    Optional<VisitDto> findAll();
    Optional<VisitDto> findById(Long id);
    public void deleteById(Long id);
    public void rearrangeVisit();
}