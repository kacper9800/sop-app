package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.sop.entities.Role;
import pl.sop.enums.ERole;
import pl.sop.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    @Test
    public void when_find_all_roles_should_return_role_list() {
        List<Role> mockRoles = new ArrayList<>();
        Role mockRole = new Role();
        mockRole.setName(ERole.ROLE_STUDENT);
        mockRoles.add(mockRole);
        Mockito.when(roleRepository.findAll()).thenReturn(mockRoles);
        ResponseEntity<List<Role>> response = roleService.findAllRoles();
        assertEquals(response.getBody().size(), mockRoles.size());
    }

    @Test
    public void when_find_all_roles_and_role_user_should_return_empty_list() {
        List<Role> mockRoles = new ArrayList<>();
        Role mockRole = new Role();
        mockRole.setName(ERole.ROLE_USER);
        mockRoles.add(mockRole);
        Mockito.when(roleRepository.findAll()).thenReturn(mockRoles);
        ResponseEntity<List<Role>> response = roleService.findAllRoles();
        assertTrue(response.getBody().isEmpty());
    }
}