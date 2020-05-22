package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sop.dto.EventDTO;
import pl.sop.dto.WorkScheduleDTO;
import pl.sop.services.WorkScheduleService;

import java.text.ParseException;
import java.util.List;

@RestController
public class WorkScheduleController {

    @Autowired
    WorkScheduleService workScheduleService;

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/planner/workSchedule", method = RequestMethod.GET)
    public ResponseEntity<List<WorkScheduleDTO>> getAllWorkSchedules() {
        final List<WorkScheduleDTO> workSchedules = workScheduleService.getAllWorkSchedules();
        return new ResponseEntity(workSchedules, HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/planner/workSchedule", method = RequestMethod.POST)
    public ResponseEntity createWorkSchedule(@RequestBody WorkScheduleDTO workScheduleDTO) {
        return ResponseEntity.ok(workScheduleService.createWorkSchedule(workScheduleDTO));
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/planner/workSchedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder deleteWorkSchedule(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Provided id is wrong!");
        }
        workScheduleService.deleteWorkSchedule(id);
        return ResponseEntity.status(HttpStatus.OK);
    }
}
