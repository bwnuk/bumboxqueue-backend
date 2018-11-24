package pl.hackyeah.bumboxqueue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.bumboxqueue.dto.VisitDto;
import pl.hackyeah.bumboxqueue.service.VisitService;

import java.sql.Time;
import java.sql.Timestamp;


@RestController
@RequestMapping("/visits")
public class VisitController {




    @PostMapping(value = "/doctor/{doctorPesel}/patient/{patientPesel}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVisit(@PathVariable String doctorPesel, @PathVariable String patientPesel, Timestamp from, Timestamp to) {
       // VisitService.saveVisit(doctorPesel,patientPesel,from,to);
    }

    @DeleteMapping(value = "/visit/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteVisit(@PathVariable long id) {
        //VisitService.deleteVisit(id);
    }
}
