package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.bumboxqueue.service.PatientService;

@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientEndpoint {
  private final PatientService patientService;

  public PatientEndpoint(PatientService patientService) {
    this.patientService = patientService;
  }
}
