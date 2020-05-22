package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.converters.FromDTO.DTOToLocationConverter;
import pl.sop.converters.ToDTO.LocationToDTOConverter;
import pl.sop.dao.entities.Location;
import pl.sop.dao.repository.LocationRepository;
import pl.sop.dto.LocationDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private final LocationToDTOConverter locationToDTOConverter = new LocationToDTOConverter();
    private final DTOToLocationConverter dtoToLocationConverter = new DTOToLocationConverter();

    public LocationService() {
    }

    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAllLocations();
        List<LocationDTO> locationDTOS = locations.stream().map(item -> locationToDTOConverter.convert(item)).collect(toList());
        return locationDTOS;
    }

    public Location createLocation(LocationDTO locationDTO) {
        Location location = dtoToLocationConverter.convert(locationDTO);
        return this.locationRepository.save(location);
    }

    public void deleteLocation(Long id) {
        this.locationRepository.deleteById(id);
    }
}
