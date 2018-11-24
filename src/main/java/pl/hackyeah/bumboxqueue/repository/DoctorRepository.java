package pl.hackyeah.bumboxqueue.repository;

import org.springframework.stereotype.Repository;
import pl.hackyeah.bumboxqueue.dto.DoctorDto;

import java.util.Optional;

@Repository
public  interface DoctorRepository {
    Optional<DoctorDto> findByName(String name);
    Optional<DoctorDto> findBySurname(String surname);
    Optional<DoctorDto> findBySpecialization(String specialization);
    Optional<DoctorDto> findAll();
}
