package pl.sop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sop.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        UserController userController = new UserController();
        userController.userService = userService;
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void when_get_all_users_should_return_ok() throws Exception {
//        List<UserDTO> mockUsers = new ArrayList<>();
//        mockUsers.add(new UserDTO());
//        when(userService.getAllUsers()).thenReturn(mockUsers);
//        //TODO: null Authentication?
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk());
    }
}