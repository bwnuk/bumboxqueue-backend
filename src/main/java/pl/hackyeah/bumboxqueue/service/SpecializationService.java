package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.entity.SpecializationEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.SpecializationRepository;

import java.util.List;

@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public List<SpecializationEntity> findAll() {
        return specializationRepository.findAll();
    }

    public SpecializationEntity saveSpecialization(SpecializationEntity specialization) {
        String name = specialization.getName();
        if (specializationRepository.findByName(name).isPresent()) {
            throw new BadRequestException(String.format("Specialization with name %s is exist", name), ServiceErrorCode.SPECIALIZATION_ALREADY_EXISTED);
        }
        return specializationRepository.save(specialization);
    }

    public void deleteSpecialization(Long id) {
        specializationRepository.deleteById(id);
    }

}
