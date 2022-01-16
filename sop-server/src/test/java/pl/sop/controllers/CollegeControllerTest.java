package pl.sop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sop.entities.organizationStructure.CollegeService;

@RunWith(MockitoJUnitRunner.class)
public class CollegeControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private CollegeService collegeService;

    @Before
    public void setup() {
        CollegeController collegeController = new CollegeController();
        //TODO: private autowire prevent use mock object
        //collegeController.collegeService = collegeService;
        mockMvc = MockMvcBuilders.standaloneSetup(collegeController).build();
    }

    @Test
    public void when_get_all_colleges_should_return_ok() throws Exception {
//        List<CollegeDTO> mockColleges = new ArrayList<>();
//        mockColleges.add(new CollegeDTO());
//        when(collegeService.findAllColleges()).thenReturn(mockColleges);
//        mockMvc.perform(get("/api/colleges"))
//                .andExpect(status().isOk());
    }
}