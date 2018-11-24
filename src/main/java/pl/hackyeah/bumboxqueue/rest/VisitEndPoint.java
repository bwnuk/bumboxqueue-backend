package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.input.VisitInputDto;
import pl.hackyeah.bumboxqueue.dto.output.VisitOutputDto;
import pl.hackyeah.bumboxqueue.service.VisitService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/visits")
public class VisitEndPoint {
    private final VisitService visitService;

    public VisitEndPoint(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitOutputDto> saveVisit(@RequestBody VisitInputDto visitInputDto){
        log.debug("Recieved POST request saveVisit");
        VisitOutputDto result = visitService.saveVisit(visitInputDto);
        log.info("Returned result={}", result);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VisitOutputDto>>findAll(){
        log.debug("Received GET request findAll");
        List<VisitOutputDto> result = visitService.findAll();
        log.info("Returned result={}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteVisit(@PathVariable Long id){
        log.debug("Received DELETE request deleteVisit");
        visitService.deleteVisit(id);
        log.info("Returned result={}", HttpStatus.NO_CONTENT);
    }
}
