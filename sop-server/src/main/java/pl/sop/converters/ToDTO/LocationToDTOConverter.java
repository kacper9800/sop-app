package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Location;
import pl.sop.dto.LocationDTO;

import java.text.ParseException;

public class LocationToDTOConverter implements Converter<Location, LocationDTO> {

    @Override
    public LocationDTO convert(Location input) {
        LocationDTO locationDTO = new LocationDTO();

        return locationDTO;
    }
}
