package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Location;
import pl.sop.dto.LocationDTO;

public class DTOToLocationConverter implements Converter<LocationDTO, Location> {
    @Override
    public Location convert(LocationDTO input) {
        Location location = new Location();
        return location;
    }
}
