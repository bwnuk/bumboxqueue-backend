package pl.hackyeah.bumboxqueue.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.entity.SpecializationEntity;
import pl.hackyeah.bumboxqueue.service.SpecializationService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/specializations")
public class SpecializationEndpoint {
    private final SpecializationService specializationService;

    public SpecializationEndpoint(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpecializationEntity> saveSpecialization(@RequestBody SpecializationEntity specializationEntity) {
        log.debug("Received POST request saveSpecialization");
        SpecializationEntity result = specializationService.saveSpecialization(specializationEntity);
        log.info("Returned result={}", result);
        return new ResponseEntity<SpecializationEntity>(result, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpecializationEntity>> findAll() {
        log.debug("Received GET request findAll");
        List<SpecializationEntity> result = specializationService.findAll();
        log.info("Returned result={}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        log.debug("Received Delete request");
        specializationService.deleteSpecialization(id);
    }
}
