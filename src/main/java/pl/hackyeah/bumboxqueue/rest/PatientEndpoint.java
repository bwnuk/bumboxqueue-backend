package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.service.PatientService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientEndpoint {
  private final PatientService patientService;

  public PatientEndpoint(PatientService patientService) {
    this.patientService = patientService;
  }

  @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMIN')")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PatientOutputDto> savePatient(@RequestBody PatientInputDto patientInputDto) {
    log.debug("Received POST request savePatient");
    PatientOutputDto result = patientService.savePatient(patientInputDto);
    log.info("Returned result={}", result);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<PatientOutputDto> getPatient(@PathVariable Long id){
    log.debug("Received GET request getPatient");
    PatientOutputDto result = patientService.getPatient(id);
    log.info("Returned result={}", result);

    return new ResponseEntity<>(result, HttpStatus.OK);
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

  @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMIN')")
  @DeleteMapping(value = "/{id}")
  public void deletePatient(@PathVariable Long id){
    log.debug("Received DELETE request deletePatient");
    patientService.deletePatient(id);
    log.info("Returned result={}", HttpStatus.NO_CONTENT);
  }
}
