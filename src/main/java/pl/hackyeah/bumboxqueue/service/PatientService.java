package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.PatientDto;
import pl.hackyeah.bumboxqueue.entity.PatientEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.PatientRepository;

import java.util.Optional;

@Service
public class PatientService {
  private final PatientRepository patientRepository;
  private final Mapper mapper;

  public PatientService(PatientRepository patientRepository, Mapper mapper) {
    this.patientRepository = patientRepository;
    this.mapper = mapper;
  }

  public PatientDto savePatient(PatientDto patientDto) {
    String pesel = patientDto.getPesel();
    if(patientRepository.findByPesel(pesel).isPresent()) {
      throw new BadRequestException(String.format("Patient with pesel %s is exist", pesel), ServiceErrorCode.PATIENT_ALREADY_EXIST);
    }
    patientDto.validate();
    patientRepository.save(mapper.map(patientDto, PatientEntity.class));
    return patientDto;
  }

  public PatientDto modifyPatient(String pesel, PatientDto patientDto){
    Optional<PatientEntity> patient = patientRepository.findByPesel(pesel);
    if(!patient.isPresent()){
      throw new BadRequestException(String.format("Patient with pesel %s doesnt exist", pesel), ServiceErrorCode
      .PATIENT_NOT_FOUND);
    }
    mapper.map(patientDto, patient.get());

    patientRepository.save(patient.get());
    return patientDto;
  }

  public void deletePatient(String pesel){
    if(!patientRepository.findByPesel(pesel).isPresent()){
      throw new BadRequestException(String.format("Patient with pesel %s doesnt exist", pesel), ServiceErrorCode
              .PATIENT_NOT_FOUND);
    }
    patientRepository.deleteById(patientRepository.findByPesel(pesel).get().getId());
  }
}
