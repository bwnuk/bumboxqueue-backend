package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.service.PatientService;

import javax.print.attribute.standard.Media;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientEndpoint {
  private final PatientService patientService;

  public PatientEndpoint(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PatientOutputDto> savePatient(@RequestBody PatientInputDto patientInputDto) {
    log.debug("Received POST request savePatient");
    PatientOutputDto result = patientService.savePatient(patientInputDto);
    log.info("Returned result={}", result);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PatientOutputDto>> findAll() {
    log.debug("Received GET request findAll");
    List<PatientOutputDto> result = patientService.findAll();
    log.info("Returned result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
