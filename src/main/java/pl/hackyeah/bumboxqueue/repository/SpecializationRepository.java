package pl.hackyeah.bumboxqueue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hackyeah.bumboxqueue.entity.SpecializationEntity;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Long> {

    Optional<SpecializationEntity> findByName(String name);

}