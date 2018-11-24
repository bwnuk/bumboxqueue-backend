package pl.hackyeah.bumboxqueue.rest;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.service.PatientService;


@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientEndpoint {

  private final PatientService patientService;

  public PatientEndpoint(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PatientOutputDto> savePatient(
      @RequestBody PatientInputDto patientInputDto) {
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

  @PutMapping(value = "/{id}")
  public ResponseEntity<PatientOutputDto> modifyPatient(@PathVariable Long id, @RequestBody PatientInputDto patientInputDto){
    log.debug("Received PUT request modifyPatient");
    PatientOutputDto result = patientService.modifyPatient(id, patientInputDto);
    log.info("Returned result={}", result);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePatient(@PathVariable Long id){
    log.debug("Received DELETE request deletePatient");
    patientService.deletePatient(id);
    log.info("Returned result={}", HttpStatus.NO_CONTENT);
  }
}
