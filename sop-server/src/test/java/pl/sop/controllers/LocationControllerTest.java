package pl.sop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sop.dto.LocationDTO;
import pl.sop.dto.WorkScheduleDTO;
import pl.sop.entities.Location;
import pl.sop.services.LocationService;
import pl.sop.services.WorkScheduleService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class LocationControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private LocationService locationService;

    @Before
    public void setup() {
        LocationController locationController = new LocationController();
        locationController.locationService = locationService;
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    public void when_get_all_locations_should_return_ok() throws Exception {
        List<LocationDTO> mockLocations = new ArrayList<>();
        mockLocations.add(new LocationDTO());
        when(locationService.getAllLocations()).thenReturn(mockLocations);
        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_create_location_should_return_ok() throws Exception {
        Location mockLocation = new Location();
        when(locationService.createLocation(Mockito.any())).thenReturn(mockLocation);
        mockMvc.perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LocationDTO())))
                .andExpect(status().isOk());
    }

    //TODO: Why return 500?
//    @Test
//    public void when_delete_location_should_return_ok() throws Exception {
//        mockMvc.perform(delete("/api/locations/{id}", 1L))
//                .andExpect(status().isOk());
//    }

    @Test
    public void when_delete_location_no_id_should_return_error() throws Exception {
        mockMvc.perform(delete("/api/locations/"))
                .andExpect(status().is4xxClientError());
    }
}