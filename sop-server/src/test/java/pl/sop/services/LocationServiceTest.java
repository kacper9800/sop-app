package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.sop.dto.LocationDTO;
import pl.sop.entities.Location;
import pl.sop.repositories.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService locationService;

    @Test
    public void when_get_all_locations_should_return_location_dto_list() {
        List<Location> mockLocations = new ArrayList<>();
        mockLocations.add(new Location());
        mockLocations.add(new Location());
        Mockito.when(locationRepository.findAllLocations()).thenReturn(mockLocations);
        List<LocationDTO> response = locationService.getAllLocations();
        assertEquals(response.size(), mockLocations.size());
    }

    @Test
    public void when_create_location_should_return_location() {
        Location mockLocation = new Location();
        mockLocation.setName("Location");
        LocationDTO mockLocationDto = new LocationDTO();
        mockLocationDto.setName("Location");
        Mockito.when(locationRepository.save(Mockito.any())).thenReturn(mockLocation);
        Location response = locationService.createLocation(mockLocationDto);
        assertEquals(response.getName(), mockLocation.getName());
    }

    @Test
    public void when_delete_location_return_nothing() {
        locationService.deleteLocation(0L);
    }
}