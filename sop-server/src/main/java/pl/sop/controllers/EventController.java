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

    @CrossOrigin
    @RequestMapping(value = "/api/event", method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        final List<EventDTO> events = eventService.getAllEvents();
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/event", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder createNewEvent(@RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder deleteEvent(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Provided id is wrong!");
        }
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.OK);
    }


}
