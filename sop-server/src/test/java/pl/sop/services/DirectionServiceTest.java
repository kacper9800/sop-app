package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.sop.dto.DirectionDTO;
import pl.sop.entities.Dictionary;
import pl.sop.entities.Direction;
import pl.sop.entities.organizationStructure.Faculty;
import pl.sop.entities.organizationStructure.Institute;
import pl.sop.enums.EDictionaryType;
import pl.sop.repositories.DirectionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DirectionServiceTest {

    @Mock
    DirectionRepository directionRepository;

    @InjectMocks
    DirectionService directionService;

    @Test
    public void when_get_by_id_return_direction() {
        Direction mockDirection = new Direction();
        mockDirection.setName("Direction");
        Mockito.when(directionRepository.findDirectionById(Mockito.any())).thenReturn(mockDirection);
        Direction response = directionService.getById(0L);
        assertEquals(response.getName(), mockDirection.getName());
    }

    @Test
    public void when_get_direction_by_id_return_direction_dto() {
        Direction mockDirection = new Direction();
        mockDirection.setName("Direction");
        mockDirection.setStudyMode(new Dictionary());
        Institute institute = new Institute();
        institute.setFaculty(new Faculty());
        mockDirection.setInstitute(institute);
        Mockito.when(directionRepository.findDirectionById(Mockito.any())).thenReturn(mockDirection);
        ResponseEntity<DirectionDTO> response = directionService.getDirectionById(0L);
        assertEquals(response.getBody().getName(), mockDirection.getName());
    }

    @Test
    public void when_delete_direction_it_should_return_null_body() {
        ResponseEntity<Object> response = directionService.deleteDirection(0L);
        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void when_find_all_directions_for_college_return_direction_dto_list() {
        List<Direction> mockDirections = new ArrayList<>();
        Direction mockDirection = new Direction();
        mockDirection.setName("Direction");
        mockDirection.setStudyMode(new Dictionary());
        Institute institute = new Institute();
        institute.setFaculty(new Faculty());
        mockDirection.setInstitute(institute);
        mockDirections.add(mockDirection);
        Mockito.when(directionRepository.findAllDirectionsForCollege(Mockito.any())).thenReturn(mockDirections);
        ResponseEntity<List<DirectionDTO>> response = directionService.getAllDirectionForCollege(0L);
        assertEquals(response.getBody().size(), mockDirections.size());
        assertEquals(response.getBody().get(0).getName(), mockDirections.get(0).getName());
    }
}