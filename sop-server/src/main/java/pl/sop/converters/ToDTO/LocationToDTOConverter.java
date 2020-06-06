package pl.sop.converters.ToDTO;

import pl.sop.converters.Converter;
import pl.sop.dao.entities.Location;
import pl.sop.dto.LocationDTO;

public class LocationToDTOConverter implements Converter<Location, LocationDTO> {

    @Override
    public LocationDTO convert(Location input) {
        LocationDTO locationDTO = new LocationDTO();
        if (input.getId() != null) {
            locationDTO.setId(input.getId());
        }

        if (input.getName() != null) {
            locationDTO.setName(input.getName());
        }

        if (input.getAddress() != null) {
            locationDTO.setAddress(input.getAddress());
        }

        if (input.getFloor() != null) {
            locationDTO.setFloor(input.getFloor());
        }

        if (input.getRoom() != null) {
            locationDTO.setRoom(input.getRoom());
        }

//        if(input.getCapacity != null) {
//            locationDTO.setCapacity(input.getCapacity());
//        }

        return locationDTO;
    }
}
