package pl.hackyeah.bumboxqueue.service;

import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.repository.DoctorRepository;

@Service
public class DoctorService {
  private final DoctorRepository doctorRepository;

  public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }
}
