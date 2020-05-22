package pl.sop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sop.dto.LocationDTO;
import pl.sop.services.LocationService;

import java.text.ParseException;
import java.util.List;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/locations", method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>> getAllEvents() {
        final List<LocationDTO> events = locationService.getAllLocations();
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/locations", method = RequestMethod.POST)
    public ResponseEntity createNewEvent(@RequestBody LocationDTO locationDTO) throws ParseException {
        return ResponseEntity.ok(locationService.createLocation(locationDTO));
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @RequestMapping(value = "/api/locations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder deleteEvent(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Provided id is wrong!");
        }
        locationService.deleteLocation(id);
        return ResponseEntity.status(HttpStatus.OK);
    }


}
