package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.bumboxqueue.service.DoctorService;

@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class DoctorEndpoint {

  private final DoctorService doctorService;

  public DoctorEndpoint(DoctorService doctorService) {
    this.doctorService = doctorService;
  }
}