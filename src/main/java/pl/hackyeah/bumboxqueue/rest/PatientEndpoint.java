package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.PatientDto;
import pl.hackyeah.bumboxqueue.service.PatientService;

@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientEndpoint {
  private final PatientService patientService;

  public PatientEndpoint(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto) {
    log.debug("Received POST request savePatient");
    PatientDto result = patientService.savePatient(patientDto);
    log.info("Returned result={}", result);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }
}
