package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.input.DoctorInputDto;
import pl.hackyeah.bumboxqueue.dto.output.DoctorOutputDto;
import pl.hackyeah.bumboxqueue.service.DoctorService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class DoctorEndpoint {

    private final DoctorService doctorService;

    public DoctorEndpoint(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorOutputDto> saveDoctor(@RequestBody DoctorInputDto doctorInputDto) {
        log.debug("Received POST request saveDoctor");
        DoctorOutputDto result = doctorService.saveDoctor(doctorInputDto);
        log.info("Returned result={}", result);
        return new ResponseEntity<DoctorOutputDto>(result, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DoctorOutputDto>> findAll() {
        log.debug("Received GET request findAll");
        List<DoctorOutputDto> result = doctorService.findAll();
        log.info("Returned result={}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        log.debug("Received Delete request");
        doctorService.deleteDoctor(id);

    }

}