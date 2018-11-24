package pl.hackyeah.bumboxqueue.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.entity.ActionType;
import pl.hackyeah.bumboxqueue.entity.HistoryEntity;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.HistoryRepository;
import pl.hackyeah.bumboxqueue.repository.PatientRepository;

@Slf4j
@Service
public class PatientService {

  private final PatientRepository patientRepository;
  private final Mapper mapper;
  private final HistoryRepository historyRepository;

  public PatientService(PatientRepository patientRepository, Mapper mapper,
      HistoryRepository historyRepository) {
    this.patientRepository = patientRepository;
    this.mapper = mapper;
    this.historyRepository = historyRepository;
  }

  @Transactional
  public PatientOutputDto savePatient(PatientInputDto patientInputDto) {
    String pesel = patientInputDto.getPesel();
    if (patientRepository.findByPesel(pesel).isPresent()) {
      throw new BadRequestException(String.format("Patient with pesel %s is exist", pesel),
          ServiceErrorCode.PATIENT_ALREADY_EXIST);
    }
    patientInputDto.validate();
    PatientEntity patientEntity = patientRepository
        .save(mapper.map(patientInputDto, PatientEntity.class));
    historyRepository.save(new HistoryEntity("", ActionType.ADD_PATIENT.getAction()));
    return mapper.map(patientEntity, PatientOutputDto.class);
  }

  public List<PatientOutputDto> findAll() {
    return mapper.mapToList(patientRepository.findAll(), PatientOutputDto.class);
  }

  @Transactional
  public PatientOutputDto modifyPatient(Long id, PatientInputDto patientInputDto) {
    Optional<PatientEntity> patient = patientRepository.findById(id);
    if (!patient.isPresent()) {
      throw new NotFoundException(String.format("Patient with id %s does not exist", id),
          ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    mapper.map(patientInputDto, patient.get());
    patientRepository.save(patient.get());
    historyRepository.save(new HistoryEntity("", ActionType.MODIFY_PATIENT.getAction()));
    return mapper.map(patient, PatientOutputDto.class);
  }

  public void deletePatient(Long id) {
    if (!patientRepository.findById(id).isPresent()) {
      throw new NotFoundException(String.format("Patient with id %s does not exist", id),
          ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    patientRepository.deleteById(id);
    historyRepository.save(new HistoryEntity("", ActionType.REMOVE_PATIENT.getAction()));
  }
}
