package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.DoctorInputDto;
import pl.hackyeah.bumboxqueue.dto.output.DoctorOutputDto;
import pl.hackyeah.bumboxqueue.entity.DoctorEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final Mapper mapper;

    public DoctorService(DoctorRepository doctorRepository, Mapper mapper) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    public Optional<DoctorEntity> getDoctorByPesel(String pesel) {
        return doctorRepository.findByPesel(pesel);
    }

    public DoctorOutputDto saveDoctor(DoctorInputDto doctorInputDto) {
        String pesel = doctorInputDto.getPesel();
        if (doctorRepository.findByPesel(pesel).isPresent()) {
            throw new BadRequestException(String.format("Doctor with pesel=%s is existed", pesel), ServiceErrorCode.DOCTOR_ALREADY_EXISTED);
        }
        //TODO: Add specialization verification,
        //if(specialization.findByName(doctorInputDto.getSpecialization()).orElseThrow(new NotFoundSpecialisation("Specialisation named=%s is existed", )
        DoctorEntity patientEntity = doctorRepository.save(mapper.map(doctorInputDto, DoctorEntity.class));
        return mapper.map(patientEntity, DoctorOutputDto.class);

    }


    /*public List<DoctorEntity> findAllDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAllBySpecializations(specialization);
    }*/

    public List<DoctorOutputDto> findAll() {
        return mapper.mapToList(doctorRepository.findAll(), DoctorOutputDto.class);
    }

    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

    public void editDoctor(DoctorEntity doctorEntity) {
        doctorRepository.save(doctorEntity);
    }


}
