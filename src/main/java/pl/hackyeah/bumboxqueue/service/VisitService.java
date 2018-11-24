package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.dto.VisitDto;
import pl.hackyeah.bumboxqueue.error.BadRequestException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.VisitRepository;

@Service
public class VisitService {
    private final DoctorService doctorService;
    private final VisitRepository visitRepository;

    public VisitService(DoctorService doctorService, VisitRepository visitRepository) {
        this.doctorService = doctorService;
        this.visitRepository = visitRepository;
    }

    public VisitDto saveVisit(VisitDto visitDto) {
        //TODO check if Patient with that PESEL exist
        //
        /*
        if(doctorService.findByPesel(visitDto).isPresent())
        {

        }
        */
        return null;
    }

    public void deleteVisit(Long id) {
        if (!visitRepository.findById(id).isPresent()) {
            throw new BadRequestException("Started visit cannot be before ended visit", ServiceErrorCode.INVALID_TIME_SYNTAX);
        }
        visitRepository.deleteById(id);
    }
}
