package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
  private final PatientRepository patientRepository;
  private final Mapper mapper;

  public PatientService(PatientRepository patientRepository, Mapper mapper) {
    this.patientRepository = patientRepository;
    this.mapper = mapper;
  }

  public PatientOutputDto savePatient(PatientInputDto patientInputDto) {
    String pesel = patientInputDto.getPesel();
    if(patientRepository.findByPesel(pesel).isPresent()) {
      throw new BadRequestException(String.format("Patient with pesel %s is exist", pesel), ServiceErrorCode.PATIENT_ALREADY_EXIST);
    }
    patientInputDto.validate();
    PatientEntity patientEntity = patientRepository.save(mapper.map(patientInputDto, PatientEntity.class));
    return mapper.map(patientEntity, PatientOutputDto.class);
  }

  public PatientOutputDto getPatient(Long id){
    Optional<PatientEntity> patient = patientRepository.findById(id);
    if(!patient.isPresent()){
      throw new NotFoundException(String.format("Patient with id %s does not exist", id), ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    return mapper.map(patient, PatientOutputDto.class);
  }

  public List<PatientOutputDto> findAll() {
    return mapper.mapToList(patientRepository.findAll(), PatientOutputDto.class);
  }

  public PatientOutputDto modifyPatient(Long id, PatientInputDto patientInputDto){
    Optional<PatientEntity> patient = patientRepository.findById(id);
    if(!patient.isPresent()){
      throw new NotFoundException(String.format("Patient with id %s does not exist", id), ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    mapper.map(patientInputDto, patient.get());
    patientRepository.save(patient.get());
    return mapper.map(patient, PatientOutputDto.class);
  }

  public void deletePatient(Long id){
    if(!patientRepository.findById(id).isPresent()){
      throw new NotFoundException(String.format("Patient with id %s does not exist", id), ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    patientRepository.deleteById(id);
  }
}
