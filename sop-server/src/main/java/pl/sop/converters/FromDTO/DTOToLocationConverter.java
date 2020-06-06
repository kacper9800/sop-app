package pl.sop.converters.FromDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Location;
import pl.sop.dto.LocationDTO;

public class DTOToLocationConverter implements Converter<LocationDTO, Location> {
    @Override
    public Location convert(LocationDTO input) {
        Location location = new Location();
        if (input.getId() != null) {
            location.setId(input.getId());
        }
        if (input.getName() != null) {
            location.setName(input.getName());
        }
        if (input.getAddress() != null) {
            location.setAddress(input.getAddress());
        }
        if (input.getFloor() != null) {
            location.setFloor(input.getFloor());
        }
        if (input.getRoom() != null) {
            location.setRoom(input.getRoom());
        }

//        if(input.getCapacity() != null) {
//            location.setCapacity(input.getCapacity());
//        }

        return location;
    }
}
