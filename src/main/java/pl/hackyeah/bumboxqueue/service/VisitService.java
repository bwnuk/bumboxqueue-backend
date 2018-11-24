package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.VisitInputDto;
import pl.hackyeah.bumboxqueue.dto.output.VisitOutputDto;
import pl.hackyeah.bumboxqueue.entity.VisitEntity;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.VisitRepository;

import java.util.List;

@Service
public class VisitService {
    private VisitRepository visitRepository;
    private Mapper mapper;

    public VisitService(VisitRepository visitRepository, Mapper mapper) {
        this.visitRepository = visitRepository;
        this.mapper = mapper;
    }

    public VisitOutputDto saveVisit(VisitInputDto visitInputDto){
        if((visitRepository.findByStartTime(visitInputDto.getStartTime(), visitInputDto.getDate()).isPresent()
                || visitRepository.findByEndTime(visitInputDto.getEndTime(), visitInputDto.getDate()).isPresent())
                && visitRepository.findByIdDctor(visitInputDto.getIdDoctor()).isPresent()){
            throw new BadRequestException(String.format("Doctor  has already visit at this time"),
                    ServiceErrorCode.DOCTOR_HAS_ALREADY_VISIT);
        }
        visitInputDto.validate();
        VisitEntity visitEntity = visitRepository.save(mapper.map(visitInputDto, VisitEntity.class));
        return mapper.map(visitEntity, VisitOutputDto.class);
    }

    public List<VisitOutputDto> findAll(){
        return mapper.mapToList(visitRepository.findAll(), VisitOutputDto.class);
    }

    public void deleteVisit(Long id){
        if(!visitRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format("Visit with id %s does not exist", id), ServiceErrorCode.VISIT_NOT_FOUND);
        }
        visitRepository.deleteById(id);
    }
}
