package pl.hackyeah.bumboxqueue.repository;

import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.dto.doctorDto;

import java.util.Optional;

@Repository
public  interface DoctorRepository {
    Optional<doctorDto> findByName(String name);
    Optional<doctorDto> findBySurname(String surname);
    Optional<doctorDto> findBySpecialization(String specialization);
    Optional<doctorDto> findAll();
}
