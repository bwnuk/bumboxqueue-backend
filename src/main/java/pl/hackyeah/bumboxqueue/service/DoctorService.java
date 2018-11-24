package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.DoctorInputDto;
import pl.hackyeah.bumboxqueue.dto.output.DoctorOutputDto;
import pl.hackyeah.bumboxqueue.entity.DoctorEntity;
import pl.hackyeah.bumboxqueue.entity.SpecializationEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.DoctorRepository;
import pl.hackyeah.bumboxqueue.repository.SpecializationRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final Mapper mapper;
    private final SpecializationRepository specializationRepository;

    public DoctorService(DoctorRepository doctorRepository, Mapper mapper, SpecializationRepository specializationRepository) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
        this.specializationRepository = specializationRepository;
    }

    public Optional<DoctorEntity> getDoctorByPesel(String pesel) {
        return doctorRepository.findByPesel(pesel);
    }

    public DoctorOutputDto saveDoctor(DoctorInputDto doctorInputDto) {
        String pesel = doctorInputDto.getPesel();
        if (doctorRepository.findByPesel(pesel).isPresent()) {
            throw new BadRequestException(String.format("Doctor with pesel=%s is existed", pesel), ServiceErrorCode.DOCTOR_ALREADY_EXISTED);
        }
        List<SpecializationEntity> specializations = specializationValidation(doctorInputDto.getSpecialization());
        DoctorEntity doctor = mapper.map(doctorInputDto, DoctorEntity.class);
        doctor.setSpecializations(specializations);
        DoctorEntity doctorEntity = doctorRepository.save(doctor);
        DoctorOutputDto doctorOutputDto = mapper.map(doctorEntity, DoctorOutputDto.class);
        doctorOutputDto.setSpecialization(doctorInputDto.getSpecialization());
        return doctorOutputDto;
    }

    public List<SpecializationEntity> specializationValidation(List<String> names) {
        List<SpecializationEntity> specializations = new LinkedList<>();
        if (!names.isEmpty())
            names.forEach(
                    specializationAsString -> {
                        Optional<SpecializationEntity> specializationEntity = specializationRepository.findByName(specializationAsString);
                        if (specializationEntity.isPresent()) {
                            specializations.add(specializationEntity.get());
                        } else
                            throw new NotFoundException(String.format("Specialization with name: %s does not exist", specializationAsString), ServiceErrorCode.SPECIALIZATION_DOES_NOT_EXIST);

                    }
            );
        return specializations;

    }

    /*public List<DoctorEntity> findAllDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAllBySpecializations(specialization);
    }*/

    public List<DoctorOutputDto> findAll() {
        return mapper.mapToList(doctorRepository.findAll(), DoctorOutputDto.class);
    }

    public DoctorOutputDto modifyDoctor(Long id, DoctorInputDto doctorInputDto) {
        Optional<DoctorEntity> doctor = doctorRepository.findById(id);
        if (!doctor.isPresent()) {
            throw new NotFoundException(String.format("Doctor with id %s does not exist", id), ServiceErrorCode
                    .DOCTOR_NOT_FOUND);
        }
        mapper.map(doctorInputDto, doctor.get());

        doctorRepository.save(doctor.get());
        return mapper.map(doctor, DoctorOutputDto.class);
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format("Doctor with id %s does not exist", id), ServiceErrorCode
                    .DOCTOR_NOT_FOUND);
        }
        doctorRepository.deleteById(id);
    }


}
