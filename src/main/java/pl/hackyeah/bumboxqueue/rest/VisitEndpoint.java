package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.bumboxqueue.dto.VisitDto;
import pl.hackyeah.bumboxqueue.service.VisitService;

@Slf4j
@RestController
@RequestMapping("/api/visits")
public class VisitEndpoint {
    private final VisitService visitService;

    public VisitEndpoint(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitDto> saveVisit(@RequestBody VisitDto visitDto) {
        log.debug("Received POST request saveVisit");
        VisitDto result = visitService.saveVisit(visitDto);
        log.info("Returned result={}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
