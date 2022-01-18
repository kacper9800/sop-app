package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sop.dto.UserDTO;
import pl.sop.dto.UserModeratorDTO;
import pl.sop.entities.ActivationKey;
import pl.sop.entities.Role;
import pl.sop.entities.User;
import pl.sop.entities.organizationStructure.*;
import pl.sop.enums.ERole;
import pl.sop.payload.request.SignUpRequest;
import pl.sop.payload.response.MessageResponse;
import pl.sop.repositories.RoleRepository;
import pl.sop.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    ActivationKeyService activationKeyService;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    FacultyService facultyService;

    @Mock
    InstituteService instituteService;

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    UserService userService;

    @Test
    public void when_find_user_should_return_user() {
        User mockUser = new User();
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        User response = userService.findUser(1L);
        assertEquals(response, mockUser);
    }

    @Test
    public void when_get_intern_basic_data_should_return_user_dto() {
        User mockUser = new User();
        mockUser.setFirstName("User");
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        UserDTO response = userService.getInternBasicData(1L);
        assertEquals(response.getFirstName(), mockUser.getFirstName());
    }

    @Test
    public void when_get_user_should_return_user_dto() {
        User mockUser = new User();
        mockUser.setFirstName("User");
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        UserDTO response = userService.getUser(1L);
        assertEquals(response.getFirstName(), mockUser.getFirstName());
    }

    @Test
    public void when_get_all_users_should_return_user_dto_list() {
        List<User> mockUsers = new ArrayList<>();
        User mockUser1 = new User();
        mockUser1.setFirstName("User1");
        mockUser1.setId(1L);
        User mockUser2 = new User();
        mockUser2.setFirstName("User2");
        mockUser2.setId(2L);
        mockUsers.add(mockUser2);
        mockUsers.add(mockUser1);
        when(userRepository.findAllUsers()).thenReturn(mockUsers);
        List<UserDTO> response = userService.getAllUsers();
        assertEquals(response.size(), mockUsers.size());
        assertEquals(response.get(0).getFirstName(), "User1");
        assertEquals(response.get(0).getId(), 1L);
    }

    @Test
    public void when_get_all_users_for_college_id_should_return_user_dto_list() {
        List<User> mockUsers = new ArrayList<>();
        User mockUser1 = new User();
        mockUser1.setFirstName("User1");
        mockUser1.setId(1L);
        User mockUser2 = new User();
        mockUser2.setFirstName("User2");
        mockUser2.setId(2L);
        mockUsers.add(mockUser2);
        mockUsers.add(mockUser1);
        when(userRepository.findAllUsersForCollegeId(Mockito.any())).thenReturn(mockUsers);
        List<UserDTO> response = userService.getAllUsersForCollegeId(1L);
        assertEquals(response.size(), mockUsers.size());
        assertEquals(response.get(0).getFirstName(), "User1");
        assertEquals(response.get(0).getId(), 1L);
    }

    @Test
    public void when_register_user_and_username_exists_should_return_error() {
        SignUpRequest request = new SignUpRequest("", "", "", "", "", "", new HashSet<>(), 1L);
        when(userRepository.existsByUsername(Mockito.any())).thenReturn(true);
        when(activationKeyService.isValidTokenForUser(Mockito.any())).thenReturn(true);
        ResponseEntity response = userService.registerUser(request, false);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(((MessageResponse)response.getBody()).getMessagee(), "Error: Username already exist!");
    }

    @Test
    public void when_register_user_and_email_exists_should_return_error() {
        SignUpRequest request = new SignUpRequest("", "", "", "", "", "", new HashSet<>(), 1L);
        when(userRepository.existsByEmail(Mockito.any())).thenReturn(true);
        when(activationKeyService.isValidTokenForUser(Mockito.any())).thenReturn(true);
        ResponseEntity response = userService.registerUser(request, false);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(((MessageResponse)response.getBody()).getMessagee(), "Error: Email is already in use!");
    }

    @Test
    public void when_register_user_should_return_ok() {
        SignUpRequest request = new SignUpRequest("token", "First", "Last", "Username", "email@test.com", "password", new HashSet<>(), 1L);
        ActivationKey activationKey = new ActivationKey("", LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), new College(), new Faculty(), new Institute(), new Department(), 20);
        activationKey.setRole("ROLE_USER");
        when(activationKeyService.isValidTokenForUser(Mockito.any())).thenReturn(true);
        when(activationKeyService.getTokenByValue(Mockito.any())).thenReturn(activationKey);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(java.util.Optional.of(new Role(ERole.ROLE_USER)));
        when(passwordEncoder.encode(Mockito.any())).thenReturn("encode_password");
        when(facultyService.findById(Mockito.any())).thenReturn(new Faculty());
        when(instituteService.findById(Mockito.any())).thenReturn(new Institute());
        when(departmentService.findById(Mockito.any())).thenReturn(new Department());
        ResponseEntity response = userService.registerUser(request, false);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((MessageResponse)response.getBody()).getMessagee(), "User registered successfully");
    }

    @Test
    public void when_change_college_should_return_ok() {
        User mockUser = new User();
        mockUser.setFirstName("User");
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        ResponseEntity response = userService.changeCollege(1L, 2L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void when_get_all_moderators_for_institute_should_return_ok() {
        User mockUser = new User();
        mockUser.setFirstName("User");
        Set<Institute> mockInstitutes = new HashSet<>();
        mockInstitutes.add(new Institute());
        mockUser.setInstitutes(mockInstitutes);
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        List<User> mockModerators = new ArrayList<>();
        mockModerators.add(new User());
        when(userRepository.findModeratorByInstituteId(Mockito.any())).thenReturn(mockModerators);
        ResponseEntity<List<UserModeratorDTO>> response = userService.getAllModeratorsForInstitute(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void when_get_all_directors_for_institute_should_return_ok() {
        User mockUser = new User();
        mockUser.setFirstName("User");
        Set<Institute> mockInstitutes = new HashSet<>();
        mockInstitutes.add(new Institute());
        mockUser.setInstitutes(mockInstitutes);
        when(userRepository.findUserById(Mockito.any())).thenReturn(mockUser);
        List<User> mockDirectors = new ArrayList<>();
        mockDirectors.add(new User());
        when(userRepository.findDirectorsByInstituteId(Mockito.any())).thenReturn(mockDirectors);
        ResponseEntity<List<UserModeratorDTO>> response = userService.getAllDirectorsForInstitute(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}