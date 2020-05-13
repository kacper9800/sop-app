package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sop.dto.EventDTO;
import pl.sop.services.EventService;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/api/event", method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        final List<EventDTO> events = eventService.getAllEvents();
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/event", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder createNewEvent(@RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.OK);
    }


}
